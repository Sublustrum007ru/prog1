package controller;

public class SiteSettings {
    private String siteURL;
    private String baseURL;
    private String categorySelector;
    private String productSelector;
    private String titleSelector;
    private String priceSelector;

    public SiteSettings(String siteURL, String baseURL, String categorySelector, String productSelector, String titleSelector, String priceSelector) {
        this.siteURL = siteURL;
        this.baseURL = baseURL;
        this.categorySelector = categorySelector;
        this.productSelector = productSelector;
        this.titleSelector = titleSelector;
        this.priceSelector = priceSelector;
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

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s | %s", siteURL, baseURL, categorySelector, productSelector, titleSelector, priceSelector);
    }


}
