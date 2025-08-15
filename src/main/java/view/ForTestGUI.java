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

public class ForTestGUI extends JFrame {

    private MainController mainController;

    private Timer timer;
    private static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd:MM:YYYY");

    private static Font font = new Font("Times New Roman", Font.BOLD, 14);

    private final int WIDHT = 1200;
    private final int HEIGTH = 800;

    private int a;
    private int b;

    private JPanel topPanel, centerPanel, bottomPanel, leftPanel, middlePanel, rightPanel;
    private JLabel lbSiteName, lbBaseURl, lnCategorySelector, lbProductSelector, lbTitleSelector, lbPriceSelector, dateLabel, clockLabel;
    private JTextArea log;
    private JTextField siteName, baseURL, categorySelector, productSelector, titleSelector, priceSelector, loadPath, savePath;
    private JButton btnLoad, btnSave, btnStart, btnLogon, btnClose;

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public ForTestGUI() {
        settings();
        createsPanels();
        setVisible(true);
    }

    private void settings() {
        setSize(WIDHT, HEIGTH);
        setResizable(false);
        setTitle("Test GUI");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createsPanels() {
        setLayout(new BorderLayout());
        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private Component createTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(createLeftPanel());
        topPanel.add(createMiddlePanel());
        topPanel.add(createRightPanel());
        return topPanel;
    }

    private Component createLeftPanel() {
        String name = "Settings";
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setMinimumSize(new Dimension(600,150));
        leftPanel.setMaximumSize(new Dimension(600,150));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        leftPanel.setBorder(setTitleBorder(name));
        lbSiteName = new JLabel("Site name");
        lbBaseURl = new JLabel("Base URL");
        lnCategorySelector = new JLabel("Category Selectro");
        lbProductSelector = new JLabel("Product Selector");
        lbTitleSelector = new JLabel("Title Selector");
        lbPriceSelector = new JLabel("Price Selector");
        siteName = new JTextField();
        baseURL = new JTextField();
        categorySelector = new JTextField();
        productSelector = new JTextField();
        titleSelector = new JTextField();
        priceSelector = new JTextField();
        JLabel[] labels = {lbSiteName, lbBaseURl, lnCategorySelector, lbProductSelector, lbTitleSelector, lbPriceSelector};
        JTextField[] fields = {siteName, baseURL, categorySelector, productSelector, titleSelector, priceSelector};
        for (int i = 0; i < labels.length; i++) {
            gbc.gridy = i / 2; // row
            gbc.gridx = (i % 2) * 2; // colums

            leftPanel.add(labels[i], gbc);
            gbc.gridx++;
            gbc.weightx = 1.0;
            gbc.ipadx = 300;
            leftPanel.add(fields[i], gbc);
            gbc.weightx = 0;
            gbc.ipadx = 0;
        }
        return leftPanel;
    }

    private Component createMiddlePanel() {
        String name = "Date / Time";
        middlePanel = new JPanel();
        middlePanel.setMinimumSize(new Dimension(120,150));
        middlePanel.setMaximumSize(new Dimension(120,150));
        middlePanel.setLayout(new GridBagLayout());
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
        String name = "Button";
        rightPanel = new JPanel();
        rightPanel.setMinimumSize(new Dimension(300,150));
        rightPanel.setMaximumSize(new Dimension(300,150));
        rightPanel.setLayout(new GridBagLayout());
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
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.clickLogonBtn();
            }
        });
        loadPath = new JTextField();
        savePath = new JTextField();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.ipadx = 100;
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
        rightPanel.add(btnStart, gbc);
        return rightPanel;
    }

    private Component createCenterPanel() {
        centerPanel = new JPanel(new GridLayout(1, 1));
        log = new JTextArea();
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(log);
        centerPanel.add(sp);
        return centerPanel;
    }

    private Component createBottomPanel() {
        bottomPanel = new JPanel();
        btnClose = new JButton("CLOSE");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bottomPanel.add(btnClose);
        JLabel gridX = new JLabel();
        JLabel gridY = new JLabel();
        JButton upX = new JButton("+");
        upX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a++;
                myRepaint();
                System.out.println("a = " + a);
            }
        });
        JButton downX = new JButton("-");
        downX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a--;
                System.out.println("a = " + a);
            }
        });
        JButton upY = new JButton("+");
        upY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b++;
                System.out.println("b = " + b);
            }
        });
        JButton downY = new JButton("-");
        downY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b--;
                System.out.println("b = " + b);
            }
        });
        gridX.setText("GridX : " + a);
        gridY.setText("GridY : " + b);
        bottomPanel.add(downX);
        bottomPanel.add(gridX);
        bottomPanel.add(upX);
        bottomPanel.add(downY);
        bottomPanel.add(gridY);
        bottomPanel.add(upY);
        return bottomPanel;
    }

    private void myRepaint(){
        getContentPane().repaint();
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

    public void closeMainGUI() {
        System.exit(0);
    }

    public void showMessage(String message) {
        log.append(message + "\n");
    }

    public void setSiteURL(String str) {
        siteName.setText(str);
    }

    public String getSiteURL() {
        return siteName.getText();
    }

    public void setBaseUrl(String str) {
        baseURL.setText(str);
    }

    public String getBaseURL() {
        return baseURL.getText();
    }

    public void setCategorySelector(String str) {
        categorySelector.setText(str);
    }

    public String getCategoreSelector() {
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

}
