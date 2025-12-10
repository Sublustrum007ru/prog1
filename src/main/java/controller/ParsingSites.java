package controller;

import controller.finds.FindBaseUrl;
import controller.finds.FindCategories;
import controller.finds.FindProducts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

    public List<String> runParsing(SiteSettings settings) throws IOException {
        List<String> list = new ArrayList<>();
        if (settings.getBaseURL().isEmpty() || settings.getBaseURL().equals("Not Found")) {
            BASE_URL = findBaseURL.getBaseUrl(settings);
            if (BASE_URL.isEmpty() || BASE_URL.equals("Not Found")) {
                showMessage("Базовый адрес не найден!!!");
            }
        } else {
            BASE_URL = settings.getBaseURL();
            list = parse(settings);
        }
        return list;
    }

    private List<String> parse(SiteSettings settings) throws IOException {
        mainController.setBaseURL(BASE_URL);
        List<String> categoryList = findCategories.find(BASE_URL, settings);
        List<String> productsList = findProducts.find(categoryList, settings);
        CheckChar checkChar = new CheckChar();
        List<String> newProductsList = checkChar.Check(productsList);
        for (String product : newProductsList) {
            showMessage(product);
        }
        showMessage("Всего найдено категорий: " + categoryList.size());
        showMessage("Всего найдено продуктов: " + productsList.size());
        return newProductsList;
    }


    public <T> void showMessage(T message) {
        mainController.message(message);
    }
}
