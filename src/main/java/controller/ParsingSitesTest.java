package controller;

import org.jsoup.nodes.Document;

import java.util.List;


public class ParsingSitesTest {

    private MainController mainController;

    private static final String PRE_SITE_NAME = "https://";
    private static String pathURL;

    private FindCategory findCategory;
    private FindProducts findProducts;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setFindCategory(FindCategory findCategory){
        this.findCategory = findCategory;
    }

    public void setFindProducts(FindProducts findProducts){
        this.findProducts = findProducts;
    }

    public void startParsing(SiteSettings settings){
        if(settings.getSiteURL().isEmpty()){
            showMessage("Имя сайта не может быть пустым");
            return;
        }else{
            pathURL = settings.getBaseURL();
        }
        ParsingSites(pathURL, settings);
    }

    private void ParsingSites(String url, SiteSettings settings) {
        try {
            showMessage("Начало парсинга категорий сайта : " + url);
            List<Category> categories = findCategory.find(url, settings);
            showMessage("\nРезультаты поиска");
            showMessage("-".repeat(80));
            int count = 1;
            for (Category category : categories) {
                showMessage(count++ + " | " + category.getName() + " | " + category.getURL());
            }
            showMessage("-".repeat(80));
            showMessage("Всего найдено категорий: " + categories.size());
            for(Category category : categories){
                parseProductInCategory(category, settings);
            }
        } catch (Exception e) {
            showMessage("Ошибка при парсинге: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void parseProductInCategory(Category category, SiteSettings settings){
        try{
            showMessage("\n[Категория] " + category.getName() + " (" + category.getURL() + ")");
            Document catDoc = new MyDocument().getDoc(category.getURL());
            List<Product> products = findProducts.find(catDoc, settings);
            if (products.isEmpty()){
                showMessage("Products not found");
                return;
            }
            showMessage("Product fount: " + products.size());
            showMessage(" " + "-".repeat(110));
            showMessage("№ | Selector | Title | Price | Price selector | Link");
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



    public void showMessage(String message) {
        mainController.message(message);
    }


}
