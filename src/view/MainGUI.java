package view;

import controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private MainController mainController;

    private final int WIDHT = 1000;
    private final int HEIGTH = 500;

    private JPanel headerPanel, footerPanel, cfgPanel;
    private JTextArea log;
    private JButton btnClose, btnStart, btnSettings;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public MainGUI() {
        settings();
        createPanels();
        setVisible(true);
    }

    private void settings() {
        setTitle("prog1");
        setSize(WIDHT, HEIGTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createPanels() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.message("Запуск сканирование сайта");
            }
        });
        btnSettings = new JButton("Settings");
        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.showMessage("Должно появиться окно настроек");
                mainController.setSettingsGUI();
            }
        });
        headerPanel.add(btnSettings);
        headerPanel.add(btnStart);
        return headerPanel;
    }


    private Component createLog() {
        log = new JTextArea();
        return log;
    }

    private Component createFooterPanel() {
        footerPanel = new JPanel();
        btnClose = new JButton("CLOSE");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.setMainGUIVisible();
            }
        });
        footerPanel.add(btnClose);
        return footerPanel;
    }

    public void closeMainGUI() {
        dispose();
    }

    public void showMessage(String message) {
        log.append(message + "\n");
    }
}
