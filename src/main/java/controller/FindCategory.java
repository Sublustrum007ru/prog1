package controller;

import controller.MyListSelectors.CategoryList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.Validator;

import java.io.IOException;
import java.util.*;

public class FindCategory {
    private static List<String> CATEGORY_SELECTORS = new CategoryList().getCategoryList();
    private static List<String> CATEGORY_NAME_SELECTORS = new CategoryList().getCategoryNameList();
    private static final Set<String> processedUrls = new HashSet<>();

    private ParsingSitesTest parsingSitesTest;

    public void setParsingSitesTest(ParsingSitesTest parsingSitesTest){
        this.parsingSitesTest = parsingSitesTest;
    }

    public List<Category> find(String URL, SiteSettings settings) throws IOException {
        if(!settings.getCategorySelector().equals("default")){
            CATEGORY_SELECTORS = Arrays.asList(settings.getCategorySelector());
        }
        processedUrls.add(URL);
        List<Category> categories = new ArrayList<>();
        Document doc = new MyDocument().getDoc(URL);
        parsingSitesTest.showMessage("\nПоиск категорий на странице: " + URL);

        for (String selector : CATEGORY_SELECTORS) {
            Elements elements = doc.select(selector);
            parsingSitesTest.showMessage("Проверка селектора '" + selector + "' найдено " + elements.size() + " элементов");

            for (Element element : elements) {
                String categoryURL = element.absUrl("href");
                String categoryName = extractCategoryName(element);
                if (Validator.isValidCategory(URL, categoryURL, categoryName)) {
                    categories.add(new Category(categoryName, categoryURL));
                }
            }
            if (!categories.isEmpty()) {
                parsingSitesTest.showMessage("Успешный селектор: " + selector);
                break;
            }
            if (categories.isEmpty()) {
                parsingSitesTest.showMessage("Поиск по селекторам не дал результатов. Пробуем поиск по паттерну ссылок....");
                Elements allLinks = doc.select("href");
                for (Element link : allLinks) {
                    String href = link.absUrl("href");
                    if (href.contains("/product-catego/") || href.contains("/catalog/") || href.contains("/category/")) {
                        String name = extractCategoryName(link);
                        categories.add(new Category(name, href));
                    }
                }
            }

            if (categories.isEmpty() && processedUrls.size() < 5) {
                parsingSitesTest.showMessage("Категории не найдены. Проверяем другие страницы....");
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    String nextURl = link.absUrl("href");
                    if (nextURl.startsWith(URL) &&
                            !processedUrls.contains(nextURl) &&
                            processedUrls.size() < 10) {
                        categories.addAll(find(nextURl, settings));
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
}
