package controller;

import controller.impl.FileOperation;
import view.MainGUI;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainController implements MainView {
    private MainGUI mainGUI;
    private LoginController loginController;
    private FileOperation fileOperation;
    private SiteSettings siteSettings;
    private FindTime time;
    private ParsingSites parseSites;
    private final Timer timer = new Timer();

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

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

    public void message(String message) {
        showMessage(message);
    }

    public void setMainGUIVisible() {
        mainGUI.closeMainGUI();
    }

    @Override
    public void showMessage(String message) {
        mainGUI.showMessage(time.findTime() + message);
    }

    public void clickLoadBtn() throws IOException {
        loadSettings();
    }

    private void loadSettings() throws IOException {
        message("Загрузка надстроек.......");
        SiteSettings temp = siteSettings.createSiteSettings(fileOperation.readFile(mainGUI.getLoadPath()));
        mainGUI.setSiteURL(temp.getSiteURL());
        mainGUI.setBaseUrl(temp.getBaseURL());
        mainGUI.setCategorySelector(temp.getCategorySelector());
        mainGUI.setProductSelector(temp.getProductSelector());
        mainGUI.setTitleSelector(temp.getTitleSelector());
        mainGUI.setPriceSelector(temp.getPriceSelector());
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
                mainGUI.getTitleSelector(),
                mainGUI.getPriceSelector())
        );
        startTimer(1000, "Succesfull");

    }

    public void saveSettingsToJson() {
        message("Сохранение надстроек в файл .json ......");
    }

    public void clickLogonBtn() {
        loginController.setVisibleLoginGUI();
    }

    public void setVisbleLogonBtn(){
        mainGUI.btnVisibleLogonOff();
    }

    public void clickLogoffBtn() {
        mainGUI.btnVisibleLogonOn();
    }

    public void clickStartBtn() {
        mainGUI.cleanLog("");
        runParse();

    }

    private void runParse() {
        message("Начато сканирование.....");
        SiteSettings settings = new SiteSettings(mainGUI.getSiteURL(),
                mainGUI.getBaseURL(),
                mainGUI.getCategorySelector(),
                mainGUI.getProductSelector(),
                mainGUI.getTitleSelector(),
                mainGUI.getPriceSelector());
        parseSites.ParsingSites(settings);
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
