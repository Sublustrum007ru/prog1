package controller;


public class SiteSettings {
    private String siteURL;
    private String baseURL;
    private String categorySelector;
    private String productSelector;
    private String paginationSelector;
    private String titleSelector;
    private String priceSelector;
    private String oldPriceSelector;
    private String currencySymbolSelector;

    public SiteSettings(String siteURL, String baseURL, String categorySelector, String productSelector, String paginationSelector, String titleSelector, String priceSelector, String oldPriceSelector, String currencySymbolSelector) {
        this.siteURL = siteURL;
        this.baseURL = baseURL;
        this.categorySelector = categorySelector;
        this.productSelector = productSelector;
        this.paginationSelector = paginationSelector;
        this.titleSelector = titleSelector;
        this.priceSelector = priceSelector;
        this.oldPriceSelector = oldPriceSelector;
        this.currencySymbolSelector = currencySymbolSelector;
    }

    public SiteSettings() {
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getCategorySelector() {
        return categorySelector;
    }

    public void setCategorySelector(String categorySelector) {
        this.categorySelector = categorySelector;
    }

    public String getProductSelector() {
        return productSelector;
    }

    public void setProductSelector(String productSelector) {
        this.productSelector = productSelector;
    }

    public String getPaginationSelector(){ return paginationSelector;}

    public void setPaginationSelector(String paginationSelector){this.paginationSelector = paginationSelector;}

    public String getTitleSelector() {
        return titleSelector;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    public String getPriceSelector() {
        return priceSelector;
    }

    public void setPriceSelector(String priceSelector) {
        this.priceSelector = priceSelector;
    }

    public String getOldPriceSelector() {
        return oldPriceSelector;
    }

    public void setOldPriceSelector(String oldPriceSelector) {
        this.oldPriceSelector = oldPriceSelector;
    }

    public String getCurrencySymbolSelector() {
        return currencySymbolSelector;
    }

    public void setCurrencySymbolSelector(String currencySymbolSelector) {
        this.currencySymbolSelector = currencySymbolSelector;
    }
//    @Override
//    public String toString() {
//        return String.format("%s | %s | %s | | %s | %s | %s | %s | %s | %s", siteURL, baseURL, categorySelector, productSelector, paginationSelector, titleSelector, priceSelector, oldPriceSelector, currencySymbolSelector);
//    }

//    @Override
//    public String toString() {
//        return  "siteURL: " + siteURL +
//                ", baseURL: " + baseURL +
//                ", categorySelector: " + categorySelector +
//                ", productSelector: " + productSelector +
//                ", paginationSelector: " + paginationSelector +
//                ", titleSelector: " + titleSelector +
//                ", priceSelector: " + priceSelector +
//                ", oldPriceSelector: " + oldPriceSelector +
//                ", currencySymbolSelector: " + currencySymbolSelector
//                ;
//    }
    @Override
    public String toString() {
        return  String.format("SiteURL: %s, BaseURL: %s, CategorySelector: %s, ProductSelector: %s, PaginationSelector: %s, TitleSelector: %s, PriceSelector: %s, OldPriceSelector: %s, CurrencySymbolSelector: %s",
                new Object[] {
                        siteURL,
                        baseURL,
                        categorySelector,
                        productSelector,
                        paginationSelector,
                        titleSelector,
                        priceSelector,
                        oldPriceSelector,
                        currencySymbolSelector
        });
    }

//    @Override
//    public String toString() {
//        return "Название=" + title +
//                " | Объем=" + volume +
//                " | Цена=" + Price +
//                " ₽";
//    }


}
