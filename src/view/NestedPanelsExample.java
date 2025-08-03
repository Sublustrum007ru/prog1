package view;


import javax.swing.*;
import java.awt.*;

public class NestedPanelsExample extends JFrame {
    public NestedPanelsExample() {
        setTitle("Вложенные панели");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 1. Верхняя панель инструментов
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.add(new JButton("Новый"));
        toolbar.add(new JButton("Открыть"));
        toolbar.add(new JButton("Сохранить"));
        add(toolbar, BorderLayout.NORTH);

        // 2. Центральная область с двумя вложенными панелями
        JPanel mainContent = new JPanel(new GridLayout(1, 2, 100, 0));

        // Левая панель
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Настройки"));
        leftPanel.add(new JCheckBox("Опция 1"));
        leftPanel.add(new JCheckBox("Опция 2"));
        leftPanel.add(new JCheckBox("Опция 3"));

        // Правая панель
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JScrollPane(new JTextArea()), BorderLayout.CENTER);

        mainContent.add(leftPanel);
        mainContent.add(rightPanel);
        add(mainContent, BorderLayout.CENTER);

        // 3. Нижняя панель статуса
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.add(new JLabel("Готово"), BorderLayout.WEST);

        JPanel clockPanel = new JPanel();
        clockPanel.add(new JLabel("12:00:00"));
        statusBar.add(clockPanel, BorderLayout.EAST);

        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }
}