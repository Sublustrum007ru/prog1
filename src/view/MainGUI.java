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

    private JPanel headerPanel, footerPanel, head, btnPanel, cfgPanel;
    private JLabel cfgPanelName, lbBaseUrl, lbCategorySelector, lbProductSelector, lbTitleSelector;
    private JTextField baseUrl, categorySelector, productSelector, titleSelector, loadPath, savePath;
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

    private Component createCfgPanel(){
        cfgPanel = new JPanel(new GridLayout(2,1));
        head = new JPanel();
        cfgPanelName = new JLabel("Settings");
        JPanel cfgFieldPanel = new JPanel(new GridLayout(2,4));
        lbBaseUrl = new JLabel("Base URL");
        baseUrl = new JTextField();
        lbCategorySelector = new JLabel("Category Selector");
        categorySelector = new JTextField();
        lbProductSelector = new JLabel("Product Selector");
        productSelector = new JTextField();
        lbTitleSelector = new JLabel("Title Selector");
        titleSelector = new JTextField();
        cfgFieldPanel.add(lbBaseUrl);
        cfgFieldPanel.add(baseUrl);
        cfgFieldPanel.add(lbCategorySelector);
        cfgFieldPanel.add(categorySelector);
        cfgFieldPanel.add(lbProductSelector);
        cfgFieldPanel.add(productSelector);
        cfgFieldPanel.add(lbTitleSelector);
        cfgFieldPanel.add(titleSelector);
        head.add(cfgPanelName);
        cfgPanel.add(head);
        cfgPanel.add(cfgFieldPanel);
        return cfgPanel;
    }

    private Component createBtnPanel(){
        btnPanel = new JPanel(new GridLayout(3,1));
        btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.message("Загрузка надстроек");
            }
        });
        btnPanel.add(btnLoad);
        loadPath = new JTextField();
        btnPanel.add(loadPath);
        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.message("Сохранение надстроек");
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
