package controller.finds;

import controller.MyDocument;
import controller.ParsingSites;
import controller.SiteSettings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FindProducts {

    private Set<String> productsList = new HashSet<>();

    private ParsingSites parsingSites;

    public void setParsingSites(ParsingSites parsingSites) {
        this.parsingSites = parsingSites;
    }

    public Set<String> testFind(Set<String> targetListURLS, SiteSettings settings) throws IOException {
        if (settings.getPaginationSelector().equals("-")) {
            find(targetListURLS, settings);
        }
        return productsList;
    }

    public Set<String> find(Set<String> tergetListURL, SiteSettings settings) throws IOException {
        for (String productsURLS : tergetListURL) {
            Document prodcutsDoc = new MyDocument().getDoc(productsURLS);
            Elements products = prodcutsDoc.getElementsByClass(settings.getProductSelector());
            for (Element product : products) {
                String titleProduct = extractTitleProduct(product, settings);
                String priceProdcut = extractPriceProdcut(product, settings);
                updateProductsList(titleProduct + " | " + priceProdcut + " ₽");
            }
        }
        return productsList;
    }

    private String extractTitleProduct(Element element, SiteSettings settings) {
        Elements tempTitleProduct = element.getElementsByClass(settings.getTitleSelector());
        String titleProduct = tempTitleProduct.text();
        return titleProduct;
    }

    private String extractPriceProdcut(Element element, SiteSettings settings) {
        String priceProduct;
        if (element.selectFirst(settings.getOldPriceSelector()) != null) {
            element.selectFirst(settings.getOldPriceSelector()).remove();
        }
        if (element.selectFirst(settings.getCurrencySymbolSelector()) != null) {
            element.selectFirst(settings.getCurrencySymbolSelector()).remove();
        }
        priceProduct = element.getElementsByClass(settings.getPriceSelector()).text();
        if (priceProduct.isEmpty()) {
            priceProduct = "Не найдена";
        }
        return priceProduct;
    }

    private void updateProductsList(String newProduct) {
        if (!productsList.contains(newProduct)) {
            productsList.add(newProduct);
        }
    }

    private <T>void showMesage(T str){
        parsingSites.showMessage(str);
    }
}
