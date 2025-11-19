package controller;

import controller.finds.FindBaseUrl;

import controller.finds.FindCategories;
import controller.finds.FindProducts;

import java.io.IOException;
import java.util.Set;


public class ParsingSites {

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

    public void setFindProducts(FindProducts findProducts) {
        this.findProducts = findProducts;
    }

    public void runParsing(SiteSettings settings) throws IOException {
        if (settings.getBaseURL().isEmpty() || settings.getBaseURL().equals("Not Found")) {
            BASE_URL = findBaseURL.getBaseUrl(settings);
        } else {
            BASE_URL = settings.getBaseURL();
        }
        mainController.setBaseURL(BASE_URL);
        findCategories.find(BASE_URL, settings);

    }


    public <T> void showMessage(T message) {
        mainController.message(message);
    }
}
