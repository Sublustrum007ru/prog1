package controller.finds;

import controller.MainView;
import controller.MyDocument;
import controller.ParsingSites;
import controller.SiteSettings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.SearchMaxNumb;

import java.io.IOException;
import java.util.*;

public class FindProducts implements MainView {
    private Set<String> productsListURLS = new HashSet<>();
    private Queue<String> urlQueue = new LinkedList<>();
    private Set<String> productsList = new HashSet<>();

    private SearchMaxNumb serchMaxNumb = new SearchMaxNumb();

    private ParsingSites parsingSites;

    public void setParsingSites(ParsingSites parsingSites) {
        this.parsingSites = parsingSites;
    }

    public List<String> find(List<String> targetsURL, SiteSettings settings) throws IOException {
        productsList.clear();
        urlQueue.clear();
        productsListURLS.clear();
        for (String targetURL : targetsURL) {
            checkPagination(targetURL, settings);
        }
        List<String> tempSortProductsListURLS = new ArrayList<>(productsListURLS);
        Collections.sort(tempSortProductsListURLS);
        extrackProduct(tempSortProductsListURLS, settings);
        List<String> sortListProdutcs = new ArrayList<>(productsList);
        Collections.sort(sortListProdutcs);
        return sortListProdutcs;
    }

    private void checkPagination(String URL, SiteSettings settings) throws IOException {
        Document doc = new MyDocument().getDoc(URL);
        Elements pagination = doc.getElementsByClass(settings.getPaginationSelector());
        if (pagination.isEmpty()) {
            updateproductsListURLS(URL);
        } else {
            for (int i = 1; i <= serchMaxNumb.search(pagination.text()); i++) {
                updateproductsListURLS(URL + "?page=" + i);
            }
        }
    }

    private void updateproductsListURLS(String newProductURL) {
        if (!productsListURLS.contains(newProductURL)) {
            productsListURLS.add(newProductURL);
            urlQueue.add(newProductURL);
        }
    }

    public void extrackProduct(List<String> targetListURLS, SiteSettings settings) throws IOException {
        for (String targetURL : targetListURLS) {
            Document prodcutsDoc = new MyDocument().getDoc(targetURL);
            Elements products = prodcutsDoc.getElementsByClass(settings.getProductSelector());
            for (Element product : products) {
                String titleProduct = extractTitleProduct(product, settings);
                String priceProdcut = extractPriceProdcut(product, settings);
                updateProductsList(titleProduct + " | " + priceProdcut + " ₽");
            }
        }
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
            priceProduct = "Цена не найдена";
        }
        return priceProduct;
    }

    private void updateProductsList(String newProduct) {
        if (!productsList.contains(newProduct)) {
            productsList.add(newProduct);
        }
    }

    @Override
    public <T> void showMessage(T message) {
        parsingSites.showMessage(message);
    }
}
