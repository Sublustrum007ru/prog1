package controller;

import controller.finds.FindBaseUrl;

import controller.finds.FindCategories;
import controller.finds.FindProducts;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;


public class ParsingSites {

    private Set<String> categoriesList = new HashSet<>();
    private Queue<String> urlQueue = new LinkedList<>();
    private Queue<String> productsQueue = new LinkedList<>();

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

    public void setFindProducts(FindProducts findProducts){this.findProducts = findProducts;}

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
        showMessage("Find categories: " + categoriesList.size());
        List<String> productsList = new ArrayList<>(findProducts.find(categoriesList, settings));
        Collections.sort(productsList);
        for(String productTest : productsList){
            showMessage(productTest);
        }
        showMessage("Find products: " + productsList.size());

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

    private void updateCategoriesList(Set<String> newCategoriesList) {
        for (String newCategory : newCategoriesList) {
            if (!categoriesList.contains(newCategory)) {
                categoriesList.add(newCategory);
                urlQueue.add(newCategory);
            }
        }
    }



    public <T> void showMessage(T message) {
        mainController.message(message);
    }
}
