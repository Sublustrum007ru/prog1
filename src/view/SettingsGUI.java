package view;

import controller.SettingsController;

import javax.swing.*;

public class SettingsGUI extends JFrame {
    private SettingsController settingsController;
    private final int WIDHT = 300;
    private final int HEIGTH = 300;

    public void setSettingsController(SettingsController settingsController){
        this.settingsController = settingsController;
    }

    public SettingsGUI(){
        settings();
        JLabel label = new JLabel("Test window");
        add(label);
        setVisible(false);
    }

    private void settings(){
        setSize(WIDHT, HEIGTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void setSettingsVisible(){
        setVisible(true);
    }

}
