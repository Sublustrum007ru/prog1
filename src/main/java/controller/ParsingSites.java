package controller;

public class ParsingSites {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void ParsingSites(SiteSettings settings) {
        showMessage("Site name: " + settings.getSiteURL() + " | Base url: " + settings.getBaseURL());
    }
    public void showMessage(String message) {
        mainController.message(message);
    }


}
