package controller;

import controller.MyListSelectors.CategoryList;
import controller.MyListSelectors.LinkList;
import controller.MyListSelectors.PriceList;
import controller.MyListSelectors.ProductList;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

import util.Validator;


public class ParsingSites {

    private MainController mainController;

    private static final String PRE_BASE_URL = "https://";
    private static String pathURL;
    private static final Set<String> processedUrls = new HashSet<>();

    private static final List<String> CATEGORY_SELECTORS = new CategoryList().getCategoryList();
    private static final List<String> CATEGORY_NAME_SELECTORS = new CategoryList().getCategoryNameList();
    private static final List<String> PRODUCT_SELECTORS = new ProductList().getProductList();
    private static final List<String> PRODUCT_NAME_SELECTORS = new ProductList().getProductNameList();
    private static final List<String> PRICE_SELECTORS = new PriceList().getPriceList();
    private static final List<String> LINK_SELECTORS = new LinkList().getLinkList();

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void ParsingSites(String BASE_URL) {
        pathURL = PRE_BASE_URL + BASE_URL;
        try {
            showMessage("Начало парсинга категорий сайта : " + pathURL);
            List<Category> categories = findCategories(pathURL);
            showMessage("\nРезультаты поиска");
            showMessage("-".repeat(80));
            int count = 1;
            for (Category category : categories) {
                showMessage(count++ + " | " + category.getName() + " | " + category.getURL());
            }
            showMessage("-".repeat(80));
            showMessage("Всего найдено категорий: " + categories.size());
            for(Category category : categories){
                parseProductInCategory(category);
            }
        } catch (Exception e) {
            showMessage("Ошибка при парсинге: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<Category> findCategories(String URL) throws IOException {
        processedUrls.add(URL);
        List<Category> categories = new ArrayList<>();
        Document doc = new MyDocument().getDoc(URL);
        showMessage("\nПоиск категорий на странице: " + URL);

        for (String selector : CATEGORY_SELECTORS) {
            Elements elements = doc.select(selector);
            showMessage("Проверка селектора '" + selector + "' найдено " + elements.size() + " элементов");

            for (Element element : elements) {
                String categoryURL = element.absUrl("href");
                String categoryName = extractCategoryName(element);
                if (Validator.isValidCategory(URL, categoryURL, categoryName)) {
                    categories.add(new Category(categoryName, categoryURL));
                }
            }
            if (!categories.isEmpty()) {
                showMessage("Успешный селектор: " + selector);
                break;
            }
            if (categories.isEmpty()) {
                showMessage("Поиск по селекторам не дал результатов. Пробуем поиск по паттерну ссылок....");
                Elements allLinks = doc.select("href");
                for (Element link : allLinks) {
                    String href = link.absUrl("href");
                    if (href.contains("/product-category/") || href.contains("/catalog/") || href.contains("/category/")) {
                        String name = extractCategoryName(link);
                        categories.add(new Category(name, href));
                    }
                }
            }

            if (categories.isEmpty() && processedUrls.size() < 5) {
                showMessage("Категории не найдены. Проверяем другие страницы....");
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    String nextURl = link.absUrl("href");
                    if (nextURl.startsWith(pathURL) &&
                            !processedUrls.contains(nextURl) &&
                            processedUrls.size() < 10) {
                        categories.addAll(findCategories(nextURl));
                        if (!categories.isEmpty()) break;
                    }
                }
            }
        }
        return categories;
    }

    private String extractCategoryName(Element element) {
        for (String selector : CATEGORY_NAME_SELECTORS) {
            Element nameElement = element.selectFirst(selector);
            if (nameElement != null && !nameElement.text().isBlank()) {
                return nameElement.text().trim();
            }
        }
        String text = element.text().trim();
        if (!text.isBlank()) {
            return text;
        }
        String title = element.attr("title");
        if (!title.isBlank()) {
            return title;
        }
        String alt = element.attr("alt");
        if (alt.isBlank()) {
            return alt;
        }
        return "Без названия";
    }

    private void parseProductInCategory(Category category){
        try{
            showMessage("\n[Категория] " + category.getName() + " (" + category.getURL() + ")");
            Document catDoc = new MyDocument().getDoc(category.getURL());
            List<Product> products = findProducts(catDoc);
            if (products.isEmpty()){
                showMessage("Products not found");
                return;
            }
            showMessage("Product fount: " + products.size());
            showMessage(" " + "-".repeat(110));
            showMessage("№ | Selector | Product Name | Price | Price selector | Link");
            showMessage(" " + "-".repeat(110));
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                showMessage(
                        i + 1 + " | " +
                        product.foundBy + " | " +
                        product.name + " | " +
                        product.price + " | " +
                        product.priceSelector + " | " +
                        product.link
                );
                showMessage(" " + "-".repeat(110));
            }
        }catch(Exception e){
            showMessage("Ошибка при парсинге категории " + category.getName() + ": " + e.getMessage());
        }
    }

    private List<Product> findProducts(Document doc){
        List<Product> products = new ArrayList<>();
        for(String selector : PRODUCT_SELECTORS){
            Elements elements = doc.select(selector);
            if(elements.isEmpty()){
                continue;
            }
            for(Element element : elements){
                Product product = new Product();
                product.foundBy = selector;

                product.name = extractProductName(element);
                product.link = extractProductLink(element);
                product.price = extractProductPrice(element);
                product.priceSelector = product.price.isEmpty() ? "Не найдена" : product.priceSelector;
                products.add(product);
            }
            if(!products.isEmpty()){
                break;
            }
        }
        return products;
    }

    private String extractProductName(Element productElement){
        for(String selector : PRODUCT_NAME_SELECTORS){
            Element element = productElement.selectFirst(selector);
            if(element != null && !element.text().isBlank()){
                return element.text().trim();
            }
        }
        return "Без названия";
    }

    private String extractProductLink(Element productElement){
        for (String selector : LINK_SELECTORS){
            Element element = productElement.selectFirst(selector);
            if(element != null){
                return element.absUrl("href");
            }
        }
        return "#";
    }
    private String extractProductPrice(Element productElement){
        for (String selctor : PRICE_SELECTORS){
            Element element = productElement.selectFirst(selctor);
            if(element != null && !element.text().isBlank()){
                return element.text().trim();
            }
        }
        return "Цена не найдена";
    }

    public void showMessage(String message) {
        mainController.message(message);
    }

    static class Produtc {
        String name;
        String link;
        String price;
        String foundBy;
        String priceSelector;

        public Produtc() {
            this.name = "Без названия";
            this.link = "#";
            this.price = "Цена не определена";
            this.foundBy = "Не определен";
            this.priceSelector = "не определен";
        }
    }

}
