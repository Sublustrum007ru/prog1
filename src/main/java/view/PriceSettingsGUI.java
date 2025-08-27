package view;

import controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PriceSettingsGUI extends JFrame {

    private MainController mainController;

    private static Font font = new Font("Times New Roman", Font.BOLD, 14);

    private final int WIDTH = 400;
    private final int HEIGTH = 122;

    private JLabel oldPrice, currencySymbol;
    private JTextField oldPriceField, currenceSymbolField;
    private JButton btnAccept, btnCansel;
    private JPanel hederPanel, footerePanel;

    public String getOldPriceField() {
        return oldPriceField.getText();
    }

    public void setOldPriceField(String str) {
        oldPriceField.setText(str);
    }

    public String getCurrenceSymbolField() {
        return currenceSymbolField.getText();
    }

    public void setCurrenceSymbolField(String str) {
        currenceSymbolField.setText(str);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public PriceSettingsGUI(){
        settings();
        createPanels();
        setVisible(false);
    }

    private void settings(){
        setTitle("Price settings");
        setSize(WIDTH, HEIGTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

    }

    private void createPanels(){
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel(){
        hederPanel = new JPanel(new GridBagLayout());
        hederPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        oldPrice = new JLabel("Old price selector");
        currencySymbol = new JLabel("Currence symbol selector");
        oldPriceField = new JTextField();
        currenceSymbolField = new JTextField();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        hederPanel.add(oldPrice, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 3.0;
        hederPanel.add(oldPriceField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        hederPanel.add(currencySymbol, gbc);
        gbc.gridx = 1;
        gbc.weightx = 3.0;
        hederPanel.add(currenceSymbolField, gbc);
        return hederPanel;
    }

    private Component createFooterPanel(){
        footerePanel = new JPanel();
        btnAccept = new JButton("Accept");
        btnAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibleGUI(false);
            }
        });
        btnCansel = new JButton("Cansel");
        btnCansel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibleGUI(false);
            }
        });
        footerePanel.add(btnAccept);
        footerePanel.add(btnCansel);
        return footerePanel;
    }

    public void setVisibleGUI(Boolean flag){
        setVisible(flag);
    }
}
