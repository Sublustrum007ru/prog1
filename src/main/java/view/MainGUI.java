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

    private final int WIDHT = 1100;
    private final int HEIGTH = 800;
    private final int LEFTPANEL_WIDHT = 715;
    private final int MIDDLEPANEL_WIDHT = 100;
    private final int RIGHTPANEL_WIDHT = 265;

    private JPanel topPanel, bottomPanel, rightPanel, leftPanel, middlePanel;
    private JLabel lbSiteName, lbBaseUrl, lbCategorySelector, lbProductSelector, lbTitleSelector, lbPriceSelector, dateLabel, clockLabel;
    private JTextField siteURL, baseUrl, categorySelector, productSelector, titleSelector, priceSelector, loadPath, savePath;
    private JTextArea log;
    private JScrollPane sp;
    private JButton btnClose, btnLogon, btnLogoff, btnStart, btnLoad, btnSave;

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
    }

    public String getBaseURL() {
        return baseUrl.getText();
    }

    public void setCategorySelector(String str) {
        categorySelector.setText(str);
    }

    public String getCategorySelector() {
        return categorySelector.getText();
    }

    public void setProductSelector(String str) {
        productSelector.setText(str);
    }

    public String getProductSelector() {
        return productSelector.getText();
    }

    public void setTitleSelector(String str) {
        titleSelector.setText(str);
    }

    public String getTitleSelector() {
        return titleSelector.getText();
    }

    public void setPriceSelector(String str) {
        priceSelector.setText(str);
    }

    public String getPriceSelector() {
        return priceSelector.getText();
    }

    public void setSavePath(String str) {
        savePath.setText(str);
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
     * setLayout(new BorderLayout()) - Задается какой менеджер компоновки будет использоваться.
     * setDefaultCloseOperaion() - Параметр задающий дествия при закрытие окна.
     */
    private void settings() {
        setTitle("prog1");
        setSize(WIDHT, HEIGTH);
        setFont(font);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createPanels() {
        setLayout(new BorderLayout());
        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private Component createTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        topPanel.add(createLeftPanel());
        topPanel.add(createMiddlePanel());
        topPanel.add(createRightPanel());
        return topPanel;
    }

    private Component createLeftPanel() {
        String panelName = "Settings";
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setMinimumSize(new Dimension(LEFTPANEL_WIDHT,120));
        leftPanel.setMaximumSize(new Dimension(LEFTPANEL_WIDHT,120));
        leftPanel.setPreferredSize(new Dimension(LEFTPANEL_WIDHT,120));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        leftPanel.setBorder(setTitleBorder(panelName));
        lbSiteName = new JLabel("Site URL");
        lbSiteName.setFont(font);
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
        siteURL = new JTextField(30);
        baseUrl = new JTextField(30);
        categorySelector = new JTextField(30);
        productSelector = new JTextField(30);
        titleSelector = new JTextField(30);
        priceSelector = new JTextField(30);
        JLabel[] labels = {lbSiteName, lbBaseUrl, lbCategorySelector, lbProductSelector, lbTitleSelector, lbPriceSelector};
        JTextField[] fields = {siteURL, baseUrl, categorySelector, productSelector, titleSelector, priceSelector};
        for (int i = 0; i < labels.length; i++) {
            gbc.gridy = i / 2;
            gbc.gridx = (i % 2) * 2;
            leftPanel.add(labels[i], gbc);
            gbc.gridx++;
            gbc.weightx = 1.0;
            leftPanel.add(fields[i], gbc);
            gbc.weightx = 0;
        }
        return leftPanel;
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

    private Component createMiddlePanel() {
        String name = "Date / Time";
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setMinimumSize(new Dimension(MIDDLEPANEL_WIDHT,120));
        middlePanel.setMaximumSize(new Dimension(MIDDLEPANEL_WIDHT, 120));
        middlePanel.setPreferredSize(new Dimension(MIDDLEPANEL_WIDHT,120));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        middlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        middlePanel.setBorder(setTitleBorder(name));
        gbc.gridy = 0;
        gbc.gridx = 0;
        middlePanel.add(createDate(), gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        middlePanel.add(cteateClock(), gbc);
        return middlePanel;
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
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        timer.start();
        updateClock();
    }

    private void updateClock() {
        LocalTime now = LocalTime.now();
        clockLabel.setText(now.format(TIME_FORMATTER));
    }

    private Component createRightPanel() {

        String name = "";
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setMinimumSize(new Dimension(RIGHTPANEL_WIDHT, 120));
        rightPanel.setMaximumSize(new Dimension(RIGHTPANEL_WIDHT, 120));
        rightPanel.setPreferredSize(new Dimension(RIGHTPANEL_WIDHT,120));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2,2,2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        rightPanel.setBorder(setTitleBorder(name));
        btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainController.clickLoadBtn();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.clickSaveBtn();
            }
        });
        btnLogon = new JButton("Logon");
        btnLogon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.clickLogonBtn();
            }
        });
        btnLogoff = new JButton("Logoff");
        btnLogoff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.clickLogoffBtn();
            }
        });
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVisibleLogonOff();
                mainController.clickStartBtn();
            }
        });
        loadPath = new JTextField();
        savePath = new JTextField();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.ipadx = 60;
        rightPanel.add(btnLoad, gbc);
        gbc.gridy = 0;
        gbc.gridx = 1;
        rightPanel.add(loadPath, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        rightPanel.add(btnSave, gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
        rightPanel.add(savePath, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        rightPanel.add(btnLogon, gbc);
        gbc.gridwidth = 1;
        rightPanel.add(btnLogoff, gbc);
        gbc.gridx = 1;
        rightPanel.add(btnStart, gbc);
        return rightPanel;
    }

    private Component createCenterPanel() {
        log = new JTextArea();
        log.setFont(font);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        sp = new JScrollPane(log);
        return sp;
    }

    private Component createBottomPanel() {
        bottomPanel = new JPanel();
        btnClose = new JButton("CLOSE");
        btnClose.setFont(font);
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.setMainGUIVisible();
            }
        });
        bottomPanel.add(btnClose);
        
        return bottomPanel;
    }

    public void closeMainGUI() {
        System.exit(0);
    }

    public void showMessage(String message) {
        log.append(message + "\n");
    }
    public void cleanLog(String message){
        log.setText(message);
    }

    public void btnVisibleLogonOn(){
        btnLogon.setVisible(true);
    }
    public void btnVisibleLogonOff(){
        btnLogon.setVisible(false);
    }
    public void btnVisibleLogoffOn(){
        btnLogoff.setVisible(true);
    }
    public void btnVisibleLogoffOff(){
        btnLogoff.setVisible(false);
    }
}
