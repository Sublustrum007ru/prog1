package view;

import controller.MainController;
import controller.SiteSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MainGUI extends JFrame {
    private MainController mainController;

    private final int WIDHT = 1000;
    private final int HEIGTH = 500;

    private JPanel headerPanel, footerPanel, head, btnPanel, cfgPanel, cfgSettingsPanel;
    private JLabel cfgPanelName, lbBaseUrl, lbCategorySelector, lbProductSelector, lbTitleSelector, lbPriceSelector, cfgSiteName;
    private JTextField baseUrl, categorySelector, productSelector, titleSelector, priceSelector, loadPath, savePath, cfgSiteField;
    private JTextArea log;
    private JButton btnClose, btnStart, btnLoad, btnSave;

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
        headerPanel.add(createCfgPanel());
        headerPanel.add(createBtnPanel());
        return headerPanel;
    }

    private Component createCfgPanel() {
        cfgPanel = new JPanel(new GridLayout(3, 1));
        head = new JPanel();
        cfgSettingsPanel = new JPanel(new GridLayout(3, 4));

        /***
         * Заголовог панели
         */
        cfgPanelName = new JLabel("Settings");
        head.add(cfgPanelName);

        /***
         * Панель содержащая настройки для парсинга сайта
         */
        cfgSiteName = new JLabel("Site URL");
        cfgSiteField = new JTextField();
        lbBaseUrl = new JLabel("Base URL");
        baseUrl = new JTextField();
        lbCategorySelector = new JLabel("Category Selector");
        categorySelector = new JTextField();
        lbProductSelector = new JLabel("Product Selector");
        productSelector = new JTextField();
        lbTitleSelector = new JLabel("Title Selector");
        titleSelector = new JTextField();
        lbPriceSelector = new JLabel("Price Selector");
        priceSelector = new JTextField();
        cfgSettingsPanel.add(cfgSiteName);
        cfgSettingsPanel.add(cfgSiteField);
        cfgSettingsPanel.add(lbBaseUrl);
        cfgSettingsPanel.add(baseUrl);
        cfgSettingsPanel.add(lbCategorySelector);
        cfgSettingsPanel.add(categorySelector);
        cfgSettingsPanel.add(lbProductSelector);
        cfgSettingsPanel.add(productSelector);
        cfgSettingsPanel.add(lbTitleSelector);
        cfgSettingsPanel.add(titleSelector);
        cfgSettingsPanel.add(lbPriceSelector);
        cfgSettingsPanel.add(priceSelector);

        /***
         * Основная панель настроек
         */
        cfgPanel.add(head);
        cfgPanel.add(cfgSettingsPanel);

        return cfgPanel;
    }

    private Component createBtnPanel() {
        btnPanel = new JPanel(new GridLayout(3, 1));
        btnLoad = new JButton("Load");
        loadPath = new JTextField();
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.message("Загрузка надстроек.....");
                mainController.readFile(loadPath.getText());
            }
        });
        btnPanel.add(btnLoad);
        btnPanel.add(loadPath);
        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.message("Сохранение надстроек......");
                SiteSettings siteSettings = new SiteSettings(cfgSiteField.getText(), baseUrl.getText(), categorySelector.getText(), productSelector.getText(), titleSelector.getText(), priceSelector.getText());
                if(loadPath.equals("")){
                    mainController.writeFile(cfgSiteField.getText(), siteSettings);
                }else{
                    mainController.writeFile(loadPath.getText(), siteSettings);
                }
            }
        });
        btnPanel.add(btnSave);
        savePath = new JTextField();
        btnPanel.add(savePath);
        btnStart = new JButton("Start");
        btnStart.setPreferredSize(new Dimension(10, 5));
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.message("Запуск сканирование сайта");
            }
        });

        btnPanel.add(btnStart);
        return btnPanel;
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
