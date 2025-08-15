package controller.MyListSelectors;

import java.util.Arrays;
import java.util.List;

public class CategoryList {

    public List<String> getCategoryList(){
        return CATEGORY_SELECTORS;
    }
    public List<String> getCategoryNameList(){
        return CATEGORY_NAME_SELECTORS;
    }

    private static final List<String> CATEGORY_SELECTORS = Arrays.asList(
            "li.product-category a",
            "a.elementor-item",
            "ul.menu li a",
            "div.categories a",
            "nav ul li a",
            "a[href*='/product-category/']"
    );

    private static final List<String> CATEGORY_NAME_SELECTORS = Arrays.asList(
            "h2",
            "h3",
            "span",
            "div.name",
            ".category-name",
            ".title"
    );
}
