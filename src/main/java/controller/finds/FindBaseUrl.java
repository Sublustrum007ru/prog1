package controller.finds;

import controller.ParsingSites;
import controller.SiteSettings;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class FindBaseUrl {

    private String PRE_BASE_URL = "https://";

    private ParsingSites parseSites;

    public void setParseSites(ParsingSites parsingSites){
        this.parseSites = parsingSites;
    }

    private List<String> listBaseUrlSelector = Arrays.asList(
            "/catalog",
            "/shop",
            "/products"
    );

    public String getBaseUrl(SiteSettings settings){
        int lenght = listBaseUrlSelector.size();
        String result = "Not Found";
        for (int i = 0; i < lenght; i++) {
            if(UrlChecker(PRE_BASE_URL+settings.getSiteURL()+listBaseUrlSelector.get(i))){
                result = PRE_BASE_URL+settings.getSiteURL()+listBaseUrlSelector.get(i);
            }
        }
        return result;
    }

    private boolean UrlChecker(String url) {
        try {
            URL tergetURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) tergetURL.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(3000);
            int time = connection.getResponseCode();
            return (time >= 200 && time <= 400);
        } catch (Exception e) {
            return false;
        }
    }
}
