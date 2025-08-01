package view;

import controller.FileOperation;
import controller.MainController;
import view.MyNotes;


public class Application{
    public static void run(){ 
        String helloMSG = "Hello World!!!\nMy name is Sublustrum007"; 
        promt(helloMSG);

        MainGUI mainGUI = new MainGUI();
        MainController mainContorller = new MainController();
        mainContorller.setMainGUI(mainGUI);
        mainContorller.setFileOperation(new FileOperation());
        mainGUI.setMainController(mainContorller);
        mainGUI.showMessage(helloMSG);

//        new MyNotes();

    } 
    public static void promt(String str){ 
        System.out.println(str); 
    } 
} 
