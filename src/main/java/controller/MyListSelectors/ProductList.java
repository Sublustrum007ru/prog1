package controller.MyListSelectors;

import java.util.Arrays;
import java.util.List;

public class ProductList {
    public List<String> getProductList(){
        return PRODUCT_SELECTORS;
    }
    public List<String> getProductNameList(){
        return PRODUCT_NAME_SELECTROS;
    }

    private static final List<String> PRODUCT_SELECTORS = Arrays.asList(
            "li.product",
            "div.product",
            ".product-item",
            ".astra-shop-thumbnail-wrap",
            ".woocommerce-product",
            ".product-grid-item",
            ".product-catd"
    );

    private static final List<String> PRODUCT_NAME_SELECTROS = Arrays.asList(
            ".prodcut-title",
            ".product__title",
            ".woocommerce-loop-product__title",
            ".product-name",
            "h2",
            "h3"
    );
}
