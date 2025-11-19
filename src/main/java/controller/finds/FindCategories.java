package controller.finds;

import controller.MyDocument;
import controller.ParsingSites;
import controller.SiteSettings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class FindCategories {

    private Set<String> categoriesList = new HashSet<>();
    private Queue<String> urlQueue = new LinkedList<>();

    private ParsingSites parsingSites;

    public void setParsingSites(ParsingSites parsingSites) {
        this.parsingSites = parsingSites;
    }

    public void find(String BASE_URL, SiteSettings settings) throws IOException {
        categoriesList.clear();
        urlQueue.clear();
        urlQueue.add(BASE_URL);
        while (!urlQueue.isEmpty()) {
            String tergetURL = urlQueue.poll();
            parsing(tergetURL, settings);
        }
        parsingSites.showMessage(categoriesList.size());
    }

    private void parsing(String URL, SiteSettings settings) throws IOException {
        try {
            Set<String> tempList = new HashSet<>();
            Document doc = new MyDocument().getDoc(URL);
            Elements categoryElements = doc.getElementsByClass(settings.getCategorySelector());
            for (Element el : categoryElements) {
                String href = el.select("a[href]").attr("href");
                tempList.add(settings.getSiteURL() + href);
            }
            updateCategoryList(tempList);
        } catch (Exception e) {
            parsingSites.showMessage("No connection to: >>>" + settings.getBaseURL() + "<<<");
        }
    }

    private void updateCategoryList(Set<String> newCategoriesList){
        for(String newCategory : newCategoriesList){
            if(!categoriesList.contains(newCategory)){
                categoriesList.add(newCategory);
                urlQueue.add(newCategory);
            }
        }
    }
}
