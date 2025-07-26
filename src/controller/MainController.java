package controller;

import view.MainGUI;

public class MainController implements MainView {
    private MainGUI mainGUI;

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public void message(String message) {
        showMessage(message);
    }

    public void setMainGUIVisible() {
        mainGUI.closeMainGUI();
    }

    @Override
    public void showMessage(String message) {
        mainGUI.showMessage(message);
    }

}
