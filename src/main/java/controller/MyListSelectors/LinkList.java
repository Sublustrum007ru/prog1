package controller.MyListSelectors;

import java.util.Arrays;
import java.util.List;

public class LinkList {

    public List<String> getLinkList(){
        return LINK_SELECTORS;
    }

    private List<String> LINK_SELECTORS = Arrays.asList(
            "a[href]",
            ".prodcut-link[href]",
            ".product-image-link[href]"
    );

}
