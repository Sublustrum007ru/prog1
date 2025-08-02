package view;

import javax.swing.*;

public class MyNotes extends JFrame {

    public MyNotes(){
        settings();
        createPanels();
        setVisible(true);
    }

    private void settings(){
        setSize(300,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createPanels(){
        JTextArea log = new JTextArea();
        String message = "Переписать чтение из ыала,\nтак что если файла нет,\nпросто вывести сообщение";
        log.setText(message);
        add(log);
    }
}
