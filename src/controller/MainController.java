package controller;

import view.MainGUI;

public class MainController implements MainView {
    private MainGUI mainGUI;
    private SettingsController settingsController = new SettingsController();

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public void message(String message) {
        showMessage(message);
    }

    public void setMainGUIVisible() {
        mainGUI.closeMainGUI();
    }

    public void setSettingsGUI(){
        settingsController.setSettingsGUIVisible();
    }

    @Override
    public void showMessage(String message) {
        mainGUI.showMessage(message);
    }

}
