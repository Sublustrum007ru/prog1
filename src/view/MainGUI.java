package view;

import controller.MainController;
import controller.SiteSettings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainGUI extends JFrame {
    private MainController mainController;

    private Timer timer;
    private static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd:MM:YYYY");
    private static Font font = new Font("Times New Roman", Font.BOLD, 14);

    private final int WIDHT = 1200;
    private final int HEIGTH = 800;

    private JPanel headerPanel, footerPanel, btnPanel, cfgPanel, cfgPanelName, cfgSettingsPanel, dateTimePanel, datePanel, clockPanel;
    private JLabel cfgName, cfgSiteName, lbBaseUrl, lbCategorySelector, lbProductSelector, lbTitleSelector, lbPriceSelector, dateLabel, clockLabel;
    private JTextField siteURL, baseUrl, categorySelector, productSelector, titleSelector, priceSelector, loadPath, savePath;
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
        headerPanel.setLayout(new GridLayout(1, 3, 30,0));
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        headerPanel.add(createCfgPanel());
        headerPanel.add(createDateTimePanel());
        headerPanel.add(createBtnPanel());
        return headerPanel;
    }

    private Component createCfgPanel() {
        String panelName = "Settings";
        cfgPanel = new JPanel(new GridLayout(1,3));
        cfgPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        cfgPanel.setBorder(setTitleBorder(panelName));
        cfgPanel.add(createCfgSettings());
        return cfgPanel;
    }

    private TitledBorder setTitleBorder(String name){
        Border baseBorder = BorderFactory.createEtchedBorder();

        // Создаем TitledBorder с выравниванием по центру
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                baseBorder,
                name,
                TitledBorder.CENTER,    // Горизонтальное выравнивание
                TitledBorder.TOP,       // Вертикальное положение
                font // Шрифт
        );
        return titledBorder;
    }

    private Component createCfgSettings() {
        cfgSettingsPanel = new JPanel(new GridLayout(3, 4));
        cfgSiteName = new JLabel("Site URL");
        cfgSiteName.setFont(font);
        lbBaseUrl = new JLabel("Base URL");
        lbBaseUrl.setFont(font);
        lbCategorySelector = new JLabel("Category Selector");
        lbCategorySelector.setFont(font);
        lbProductSelector = new JLabel("Product Selector");
        lbProductSelector.setFont(font);
        lbTitleSelector = new JLabel("Title Selector");
        lbTitleSelector.setFont(font);
        lbPriceSelector = new JLabel("Price Selector");
        lbPriceSelector.setFont(font);
        siteURL = new JTextField(25);
        baseUrl = new JTextField(25);
        categorySelector = new JTextField(25);
        productSelector = new JTextField(25);
        titleSelector = new JTextField(25);
        priceSelector = new JTextField(25);
        cfgSettingsPanel.add(cfgSiteName);
        cfgSettingsPanel.add(siteURL);
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
        return cfgSettingsPanel;
    }

    private Component createDateTimePanel() {
        String panelName = "Date / Time";
        dateTimePanel = new JPanel(new GridLayout(2, 1));
        dateTimePanel.setSize(10,10);
        dateTimePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        dateTimePanel.setBorder(setTitleBorder(panelName));
        dateTimePanel.add(createDate(), BorderLayout.NORTH);
        dateTimePanel.add(cteateClock(), BorderLayout.CENTER);
        return dateTimePanel;
    }

    private Component createDate() {
        dateLabel = new JLabel("",SwingConstants.CENTER);
        LocalDate date = LocalDate.now();
        dateLabel.setText(date.format(DATE_FORMATTER));
        dateLabel.setFont(font);
        return dateLabel;
    }

    private Component cteateClock() {
        clockLabel = new JLabel("", SwingConstants.CENTER);
        clockLabel.setFont(font);
        initTimer();
        return clockLabel;
    }

    private void initTimer() {
        timer = new Timer(1000, e -> updateClock());
        timer.start();
        updateClock();
    }

    private void updateClock() {
        LocalTime now = LocalTime.now();
        clockLabel.setText(now.format(TIME_FORMATTER));
    }

    private Component createBtnPanel() {
        btnPanel = new JPanel(new GridLayout(3, 2));
        btnPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        JPanel loadPanel = new JPanel(new GridLayout(1,2));
        btnLoad = new JButton("Load");
        btnLoad.setFont(font);
        loadPath = new JTextField(25);
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SiteSettings> list = new ArrayList<>();
                mainController.message("Загрузка надстроек.....");
                list = mainController.readFile(loadPath.getText());
                for (SiteSettings temp : list) {
                    siteURL.setText(temp.getSiteURL());
                    baseUrl.setText(temp.getBaseURL());
                    categorySelector.setText(temp.getCategorySelector());
                    productSelector.setText(temp.getProductSelector());
                    titleSelector.setText(temp.getTitleSelector());
                    priceSelector.setText(temp.getPriceSelector());
                }
            }
        });
        loadPanel.add(btnLoad);
        loadPanel.add(loadPath);
        btnPanel.add(loadPanel);
        JPanel savePanel = new JPanel(new GridLayout(1,2));
        btnSave = new JButton("Save");
        btnSave.setFont(font);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.message("Сохранение надстроек......");
                SiteSettings siteSettings = new SiteSettings(siteURL.getText(), baseUrl.getText(), categorySelector.getText(), productSelector.getText(), titleSelector.getText(), priceSelector.getText());
                if (savePath.equals("")) {
                    mainController.writeFile("default.txt", siteSettings);
                } else {
                    mainController.writeFile(savePath.getText(), siteSettings);
                }
            }
        });
        savePath = new JTextField(25);
        savePanel.add(btnSave);
        savePanel.add(savePath);
        btnPanel.add(savePanel);
        btnStart = new JButton("Start");
        btnStart.setFont(font);
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
        log.setFont(font);
        return log;
    }

    private Component createFooterPanel() {
        footerPanel = new JPanel();
        btnClose = new JButton("CLOSE");
        btnClose.setFont(font);
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
