package controller;

import controller.MyListSelectors.LinkList;
import controller.MyListSelectors.PriceList;
import controller.MyListSelectors.ProductList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FindProducts {
    private ParsingSitesTest parsingSitesTest;

    private static List<String> PRODUCT_SELECTORS = new ProductList().getProductList();
    private static final List<String> PRODUCT_NAME_SELECTORS = new ProductList().getProductNameList();
    private static List<String> PRICE_SELECTORS = new PriceList().getPriceList();
    private static final List<String> LINK_SELECTORS = new LinkList().getLinkList();

    public void setParsingSitesTest(ParsingSitesTest parsingSitesTest){
        this.parsingSitesTest = parsingSitesTest;
    }

    public List<Product> find(Document doc, SiteSettings settings) {
        if(!settings.getProductSelector().equals("default")){
            PRICE_SELECTORS = Arrays.asList(settings.getProductSelector());
        }
        List<Product> products = new ArrayList<>();
        for (String selector : PRODUCT_SELECTORS) {
            Elements elements = doc.select(selector);
            if (elements.isEmpty()) {
                continue;
            }
            for (Element element : elements) {
                Product product = new Product();
                product.foundBy = selector;

                product.name = extractProductName(element);
                product.link = extractProductLink(element, settings);
                product.price = extractProductPrice(element, settings);
                product.priceSelector = product.price.isEmpty() ? "Не найдена" : product.priceSelector;
                products.add(product);
            }
            if (!products.isEmpty()) {
                break;
            }
        }
        return products;
    }

    private String extractProductName(Element productElement) {
        for (String selector : PRODUCT_NAME_SELECTORS) {
            Element element = productElement.selectFirst(selector);
            if (element != null && !element.text().isBlank()) {
                return element.text().trim();
            }
        }
        return "Без названия";
    }

    private String extractProductLink(Element productElement, SiteSettings settings) {
        for (String selector : LINK_SELECTORS) {
            Element element = productElement.selectFirst(selector);
            if (element != null) {
                return element.absUrl("href");
            }
        }
        return "#";
    }

    private String extractProductPrice(Element productElement, SiteSettings settings) {
        if(!settings.getPriceSelector().equals("default")){
            PRICE_SELECTORS = Arrays.asList(settings.getPriceSelector());
        }
        for (String selctor : PRICE_SELECTORS) {
            Element element = productElement.selectFirst(selctor);
            if (element != null && !element.text().isBlank()) {
                return element.text().trim();
            }
        }
        return "Цена не найдена";
    }
}
