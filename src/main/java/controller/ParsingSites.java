package controller;

import controller.finds.*;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;


public class ParsingSites implements MainView{

    private Set<String> categoriesList = new HashSet<>();
    private Queue<String> urlQueue = new LinkedList<>();

    private String BASE_URL;
    private FindBaseUrl findBaseURL;

    private MainController mainController;
    private FindCategories findCategories;
    private FindProducts findProducts;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setFindBaseURL(FindBaseUrl findBaseURL) {
        this.findBaseURL = findBaseURL;
    }

    public void setFindCategories(FindCategories findCategories) {
        this.findCategories = findCategories;
    }

    public void setTestFindProduct(FindProducts findProducts){
        this.findProducts = findProducts;
    }

    public void runParse(SiteSettings settings) throws IOException, InterruptedException {
        if (settings.getBaseURL().isEmpty() || settings.getBaseURL().equals("Not Found")) {
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
        showMessage("Найдено категорий: " + categoriesList.size());
        List<String> productsList = findProducts.find(categoriesList, settings);
        for(String productTest : productsList){
            showMessage(productTest);
        }
        showMessage("Найдено товаров: " + productsList.size());
    }

    private void parsingSites(String URL, SiteSettings settings) throws IOException {
        try {
            Document doc = new MyDocument().getDoc(URL);
            Set<String> temp = null;
            temp = findCategories.find(doc, settings);
            updateCategoriesList(temp);
            for(String str : temp){
                System.out.println(str);
            }
            Set<String> tempCategoriesList = findCategories.find(doc, settings);
            updateCategoriesList(tempCategoriesList);
        } catch (Exception e) {
            showMessage("No connection to: >>>" + settings.getBaseURL() + "<<<");
        }
    }

    private void updateCategoriesList(Set<String> newCategoriesList) {
        for (String newCategory : newCategoriesList) {
            if (!categoriesList.contains(newCategory)) {
                categoriesList.add(newCategory);
                urlQueue.add(newCategory);
            }
        }
    }


    @Override
    public <T> void showMessage(T message) {
        mainController.message(message);
    }
}
