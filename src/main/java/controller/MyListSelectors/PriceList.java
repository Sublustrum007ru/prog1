package controller.MyListSelectors;

import java.util.Arrays;
import java.util.List;

public class PriceList {
    public List<String> getPriceList(){
        return PRICE_SELECTORS;
    }
    private List<String> PRICE_SELECTORS = Arrays.asList(
            ".price",
            ".product-price",
            ".woocommerce-Price-amount amount",
            ".amount",
            ".product__price",
            "span.price"
    );
}
