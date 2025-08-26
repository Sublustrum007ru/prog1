package controller.finds;

import controller.ParsingSites;
import controller.SiteSettings;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

import java.util.HashSet;
import java.util.Set;

public class FindCategories {

    private Set<String> categoriesList = new HashSet<>();

    private ParsingSites parsingSites;

    public void setParsingSites(ParsingSites parsingSites){
        this.parsingSites = parsingSites;
    }

    public Set<String> find(Document doc, SiteSettings settings){
        Elements categoriesLink = doc.getElementsByClass(settings.getCategorySelector());
        if (!categoriesLink.isEmpty()) {
            for (Element categories : categoriesLink) {
                String link = categories.selectFirst("a[href]").absUrl("href"); // Вытаскиваем ссылку на категорию
                categoriesList.add(link);
            }
        }
        return categoriesList;
    }
}
