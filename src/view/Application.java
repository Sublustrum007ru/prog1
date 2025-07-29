package view;

import controller.MainController;


public class Application{
    public static void run(){ 
        String helloMSG = "Hello World!!!\nMy name is Sublustrum007"; 
        promt(helloMSG);

        MainGUI mainGUI = new MainGUI();
        MainController mainContorller = new MainController();
        mainContorller.setMainGUI(mainGUI);
        mainGUI.setMainController(mainContorller);
        mainGUI.showMessage(helloMSG);

    } 
    public static void promt(String str){ 
        System.out.println(str); 
    } 
} 
