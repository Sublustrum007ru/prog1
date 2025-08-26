package controller;

import controller.finds.FindBaseUrl;

import controller.finds.FindCategories;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;


public class ParsingSites {

    private Set<String> categoriesList = new HashSet<>();
    private Queue<String> urlQueue = new LinkedList<>();
    private Set<String> productsList = new HashSet<>();
    private Queue<String> productsQueue = new LinkedList<>();

    private String BASE_URL;
    private FindBaseUrl findBaseURL;

    private MainController mainController;
    private FindCategories findCategories;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setFindBaseURL(FindBaseUrl findBaseURL) {
        this.findBaseURL = findBaseURL;
    }

    public void setFindCategories(FindCategories findCategories) {
        this.findCategories = findCategories;
    }

    public void runParse(SiteSettings settings) throws IOException, InterruptedException {
        if (settings.getBaseURL().isEmpty()) {
            BASE_URL = findBaseURL.getBaseUrl(settings);
        } else {
            BASE_URL = settings.getBaseURL();
        }
        mainController.setBaseURL(BASE_URL);
        urlQueue.add(BASE_URL);
        while (!urlQueue.isEmpty()) {
            String tergetURL = urlQueue.poll();
            parsingSites(tergetURL, settings);
        }
        showMessage("Find categories: " + categoriesList.size());
        findProducts(categoriesList, settings);
        showMessage("Find products: " + productsList.size());
        List<String> sortProductList = new ArrayList<>(productsList);
        Collections.sort(sortProductList);
        for (String product : sortProductList) {
            showMessage(product);
        }

    }


    private void parsingSites(String URL, SiteSettings settings) throws IOException {
        try {
            Document doc = new MyDocument().getDoc(URL);
            Set<String> tempCategoriesList = findCategories.find(doc, settings);
            updateCategoriesList(tempCategoriesList);
        } catch (Exception e) {
            showMessage("No connection to: >>>" + settings.getBaseURL() + "<<< | " + e.getMessage());
        }
    }

    private void findProducts(Set<String> productsListURLS, SiteSettings settings) throws IOException {
        for (String productsURLS : productsListURLS) {
            Document prodcutsDoc = new MyDocument().getDoc(productsURLS);
            Elements products = prodcutsDoc.getElementsByClass(settings.getProductSelector());
            for (Element product : products) {
                String titleProduct = extractTitleProduct(product, settings);
                String priceProdcut = extractPriceProdcut(product, settings);
                updateProductsList(titleProduct + " | " + priceProdcut);
            }
        }
    }

    private String extractTitleProduct(Element element, SiteSettings settings) {
        Elements tempTitleProduct = element.getElementsByClass(settings.getTitleSelector());
        String titleProduct = tempTitleProduct.text();
        return titleProduct;
    }

    private String extractPriceProdcut(Element element, SiteSettings settings) {
        String priceProduct = "Не найдена";
        if (element.selectFirst("del") != null) {
            element.selectFirst("del").remove();
        }
        if(element.selectFirst("span.woocommerce-Price-currencySymbol") != null){
            element.selectFirst("span.woocommerce-Price-currencySymbol").remove();
        }
        priceProduct = element.getElementsByClass("woocommerce-Price-amount").text();
        return priceProduct + " ₽";
    }

    private void updateCategoriesList(Set<String> newCategoriesList) {
        for (String newCategory : newCategoriesList) {
            if (!categoriesList.contains(newCategory)) {
                categoriesList.add(newCategory);
                urlQueue.add(newCategory);
            }
        }
    }

    private void updateProductsList(String newProduct) {
        if (!productsList.contains(newProduct)) {
            productsList.add(newProduct);
        }
    }


    public <T> void showMessage(T message) {
        mainController.message(message);
    }
}
