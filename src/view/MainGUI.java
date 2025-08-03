package view;

import controller.MainController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainGUI extends JFrame {
    private MainController mainController;

    private Timer timer;
    private static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd:MM:YYYY");
    private static Font font = new Font("Times New Roman", Font.BOLD, 14);

    private final int WIDHT = 1200;
    private final int HEIGTH = 800;

    private JPanel headerPanel, footerPanel, btnPanel, cfgPanel, cfgSettingsPanel, dateTimePanel;
    private JLabel cfgSiteName, lbBaseUrl, lbCategorySelector, lbProductSelector, lbTitleSelector, lbPriceSelector, dateLabel, clockLabel;

    private JTextField siteURL, baseUrl, categorySelector, productSelector, titleSelector, priceSelector, loadPath, savePath;
    private JTextArea log;
    private JScrollPane sp;
    private JButton btnClose, btnStart, btnLoad, btnSave;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setSiteURL(String str) {
        siteURL.setText(str);
    }

    public String getSiteURL() {
        return siteURL.getText();
    }

    public void setBaseUrl(String str) {
        baseUrl.setText(str);
        ;
    }

    public String getBaseURL() {
        return baseUrl.getText();
    }

    public void setCategorySelector(String str) {
        categorySelector.setText(str);
        ;
    }

    public String getCategoreSelector() {
        return categorySelector.getText();
    }

    public void setProductSelector(String str) {
        productSelector.setText(str);
        ;
    }

    public String getProductSelector() {
        return productSelector.getText();
    }

    public void setTitleSelector(String str) {
        titleSelector.setText(str);
        ;
    }

    public String getTitleSelector() {
        return titleSelector.getText();
    }

    public void setPriceSelector(String str) {
        priceSelector.setText(str);
        ;
    }

    public String getPriceSelector() {
        return priceSelector.getText();
    }

    public void setSavePath(String str) {
        savePath.setText(str);
        ;
    }

    public String getSavePath() {
        return savePath.getText();
    }

    public void setLoadPatch(String str) {
        loadPath.setText(str);
    }

    public String getLoadPath() {
        return loadPath.getText();
    }

    public MainGUI() {
        settings();
        createPanels();
        setVisible(true);
    }

    /***
     * Задаются параметры создаваемого окна.
     * setTitle() - Имя окна, выводиться как обычно. В рамке окна.
     * setSize(int WIDHT, int HEIGTH) - Размеры окна. Указываются в целых числах.
     * setResizable() - Если в скобках написано "true", то пользваотелю разрешено изменять размер окна. Если "false", то запрещено.
     * setLocationRelativeTo(null) - Позиионирование создоваемого окна. "null" - позиционирование опцентру экрана.
     * setDefaultCloseOperaion() - Параметр задающий дествия при закрытие окна.
     */
    private void settings() {
        setTitle("prog1");
        setSize(WIDHT, HEIGTH);
        setFont(font);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createPanels() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel() {
        headerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 284;
        gbc.ipady = 10;
        headerPanel.add(createCfgPanel(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        gbc.ipady = 35;
        headerPanel.add(createDateTimePanel(), gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 180;
        gbc.ipady = 10;
        headerPanel.add(createBtnPanel(), gbc);
        return headerPanel;
    }

    private Component createCfgPanel() {
        String panelName = "Settings";
        cfgPanel = new JPanel(new GridLayout(1, 3));
        cfgPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        cfgPanel.setBorder(setTitleBorder(panelName));
        cfgPanel.add(createCfgSettings());
        return cfgPanel;
    }

    private TitledBorder setTitleBorder(String name) {
        Border baseBorder = BorderFactory.createEtchedBorder();

        /***
         * name - Имя панели.
         * TitleBorder.CENTER - Выравнивание по горизонтале
         * TitleBorder.TOP - Выравнивание по вертикали
         * font- Шрифт. В данном примере задан в самом начале.
         */
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                baseBorder,
                name,
                TitledBorder.CENTER,
                TitledBorder.TOP,
                font
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
        siteURL = new JTextField(50);
        baseUrl = new JTextField(50);
        categorySelector = new JTextField(50);
        productSelector = new JTextField(50);
        titleSelector = new JTextField(50);
        priceSelector = new JTextField(50);
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
        String panelName = "Date";
        dateTimePanel = new JPanel(new GridLayout(2, 1));
        dateTimePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        dateTimePanel.setBorder(setTitleBorder(panelName));
        dateTimePanel.add(createDate(), BorderLayout.NORTH);
        dateTimePanel.add(cteateClock(), BorderLayout.CENTER);
        return dateTimePanel;
    }

    private Component createDate() {
        dateLabel = new JLabel("", SwingConstants.CENTER);
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
        JPanel loadPanel = new JPanel(new GridLayout(1, 2));
        btnLoad = new JButton("Load");
        btnLoad.setFont(font);
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainController.loadSettings();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        loadPath = new JTextField(25);
        loadPath.setFont(font);
        loadPanel.add(btnLoad);
        loadPanel.add(loadPath);
        btnPanel.add(loadPanel);

        JPanel savePanel = new JPanel(new GridLayout(1, 2));
        btnSave = new JButton("Save");
        btnSave.setFont(font);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.writeSettings();
                mainController.saveSettingsToJson();
            }
        });
        savePath = new JTextField(25);
        savePath.setFont(font);
        savePanel.add(btnSave);
        savePanel.add(savePath);

        btnPanel.add(savePanel);
        btnStart = new JButton("Start");
        btnStart.setFont(font);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.startParce();
            }
        });
        btnPanel.add(btnStart);
        return btnPanel;
    }

    private Component createLog() {
        log = new JTextArea();
        log.setFont(font);
        sp = new JScrollPane(log);
        return sp;
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
        System.exit(0);
    }

    public void showMessage(String message) {
        log.append(message + "\n");
    }
}
