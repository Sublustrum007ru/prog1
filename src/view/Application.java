package view;

import controller.FileOperation;
import controller.FindTime;
import controller.MainController;
import controller.SiteSettings;
import view.MyNotes;

import java.io.File;


public class Application{
    public static void run(){ 
        String helloMSG = "Hello World!!!\nMy name is Sublustrum007"; 
        promt(helloMSG);

        MainGUI mainGUI = new MainGUI();
        MainController mainContorller = new MainController();
        mainContorller.setMainGUI(mainGUI);
        FileOperation fileOperation = new FileOperation();
        mainContorller.setFileOperation(fileOperation);
        fileOperation.setMainController(mainContorller);
        mainContorller.setSiteSettings(new SiteSettings());
        FindTime time = new FindTime();
        mainContorller.setTime(time);
        mainGUI.setMainController(mainContorller);
        mainGUI.showMessage(helloMSG);

//        new MyNotes();

    } 
    public static void promt(String str){ 
        System.out.println(str); 
    } 
} 
