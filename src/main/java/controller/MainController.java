package controller;

import controller.finds.FindTime;
import controller.impl.FileOperation;
import view.MainGUI;
import view.PriceSettingsGUI;
import view.ProductsSettingsGUI;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainController implements MainView {
    private MainGUI mainGUI;
    private PriceSettingsGUI priceSettingsGUI;
    private ProductsSettingsGUI productSettingsGUI;
    private LoginController loginController;
    private FileOperation fileOperation;
    private SiteSettings siteSettings;
    private FindTime time;
    private ParsingSites parseSites;
    private final Timer timer = new Timer();

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public void setPriceSettingsGUI(PriceSettingsGUI priceSettingsGUI){this.priceSettingsGUI = priceSettingsGUI;}
    public void setProductSettingsGUI(ProductsSettingsGUI productSettingsGUI){this.productSettingsGUI = productSettingsGUI;}

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setFileOperation(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    public void setSiteSettings(SiteSettings siteSettings) {
        this.siteSettings = siteSettings;
    }

    public void setParseSites(ParsingSites parsingSites){
        this.parseSites = parsingSites;
    }

    public void setTime(FindTime time) {
        this.time = time;
    }

    public <T>void message(T message) {
        showMessage(message);
    }

    public void clickBtnClose() {
        mainGUI.closeMainGUI();
    }

    @Override
    public <T>void showMessage(T message) {
        mainGUI.showMessage(time.findTime() + message);
    }

    public void clickPriceSettingsBtn(){
        priceSettingsGUI.setVisibleGUI(true);
    }
    public void clickBtnProductSettings(){
        productSettingsGUI.setVisibleGUI(true);
    }

    public void clickLoadBtn() throws IOException {
        loadSettings();
    }

    private void loadSettings() throws IOException {
        message("Загрузка надстроек.......");
        SiteSettings setting = fileOperation.readFile(mainGUI.getLoadPath());
        mainGUI.setSiteURL(setting.getSiteURL());
        mainGUI.setBaseUrl(setting.getBaseURL());
        mainGUI.setCategorySelector(setting.getCategorySelector());
        mainGUI.setProductSelector(setting.getProductSelector());
        productSettingsGUI.setPaginationSelector(setting.getPaginationSelector());
        mainGUI.setTitleSelector(setting.getTitleSelector());
        mainGUI.setPriceSelector(setting.getPriceSelector());
        priceSettingsGUI.setOldPriceField(setting.getOldPriceSelector());
        priceSettingsGUI.setCurrenceSymbolField(setting.getCurrencySymbolSelector());
        message("Succesfull");

    }

    public void clickSaveBtn() {
        writeSettings();
//        saveSettingsToJson();
    }

    private void writeSettings() {
        message("Сохранение надстроек......");
        fileOperation.writeFile(mainGUI.getSavePath(), new SiteSettings(
                mainGUI.getSiteURL(),
                mainGUI.getBaseURL(),
                mainGUI.getCategorySelector(),
                mainGUI.getProductSelector(),
                productSettingsGUI.getPaginationSelector(),
                mainGUI.getTitleSelector(),
                mainGUI.getPriceSelector(),
                priceSettingsGUI.getOldPriceField(),
                priceSettingsGUI.getCurrenceSymbolField())
        );
        startTimer(1000, "Succesfull");

    }

    public void saveSettingsToJson() {
        message("Сохранение надстроек в файл .json ......");
    }

    public void clickLogonBtn() {
        loginController.setVisibleLoginGUI();

    }

    public void setVisbleLogonBtn(Boolean flag){
        mainGUI.switchBtnsVisible(flag);
    }

    public void clickBtnLogoff() {
        mainGUI.switchBtnsVisible(true);
    }

    public void clickBtnStart() throws IOException, InterruptedException {
        mainGUI.cleanLog("");
        runParse();

    }

    private void runParse() throws IOException, InterruptedException {
        message("Начато сканирование.....");
        SiteSettings settings = new SiteSettings(
                mainGUI.getSiteURL(),
                mainGUI.getBaseURL(),
                mainGUI.getCategorySelector(),
                mainGUI.getProductSelector(),
                productSettingsGUI.getPaginationSelector(),
                mainGUI.getTitleSelector(),
                mainGUI.getPriceSelector(),
                priceSettingsGUI.getOldPriceField(),
                priceSettingsGUI.getCurrenceSymbolField()
        );
        parseSites.runParse(settings);
    }

    public void setBaseURL(String BASE_URL){
        mainGUI.setBaseUrl(BASE_URL);
    }

    public void startTimer(int time, String message) {
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                message(message);
            }
        };
        timer.schedule(task1, time);
    }

}
