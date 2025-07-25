package controller;

import view.SettingsGUI;

public class SettingsController {
    private SettingsGUI settingsGUI;

    public void setSettingsGUI(SettingsGUI settingsGUI){
        this.settingsGUI = settingsGUI;
    }

    public void setSettingsGUIVisible(){
        settingsGUI.setSettingsVisible();
    }
}
