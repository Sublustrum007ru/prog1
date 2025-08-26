package controller;

import controller.finds.FindBaseUrl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class ParsingSites {

    private String BASE_URL;
    private FindBaseUrl findBaseURL;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setFindBaseURL(FindBaseUrl findBaseURL){this.findBaseURL = findBaseURL;}

    public void ParsingSites(SiteSettings settings) throws IOException {
        if(settings.getBaseURL().isEmpty()){
            BASE_URL = findBaseURL.getBaseUrl(settings);
        }else{
            BASE_URL = settings.getBaseURL();
        }
        mainController.setBaseURL(BASE_URL);
        try{
            Document doc = Jsoup.connect(BASE_URL).get();
            Elements elements = doc.getElementsByClass("product col-md-2 col-sm-6 col-6"); // Category selector
            for (Element element : elements){
                Elements elementsTitle = element.getElementsByClass("product-title");
                showMessage(elementsTitle.text());
            }
        }catch(Exception e){
            showMessage("No connection to: " + BASE_URL);
        }
    }
    public <T>void showMessage(T message) {
        mainController.message(message);
    }
}
