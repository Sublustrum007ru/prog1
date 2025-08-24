package controller.MyListSelectors;

import java.util.Arrays;
import java.util.List;

public class PriceList {
    public List<String> getPriceListCss(){
        return PRICE_SELECTORS_CSS;
    }
    private List<String> PRICE_SELECTORS_CSS = Arrays.asList(
            ".price",
            ".product-price",
            ".woocommerce-Price-amount amount",
            ".amount",
            ".product__price"
    );

    public List<String> getPriceListClass(){return PRICE_SELECTOR_CLASS;}
    private List<String> PRICE_SELECTOR_CLASS = Arrays.asList(
            "span.price"
    );
}
