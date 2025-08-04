package view;

import controller.LoginController;
import controller.impl.FileOperation;
import controller.FindTime;
import controller.MainController;
import controller.SiteSettings;


public class Application{
    public static void run(){ 
        String helloMSG = "Hello World!!!\nMy name is Sublustrum007"; 
        promt(helloMSG);

        MainGUI mainGUI = new MainGUI();
        MainController mainContorller = new MainController();
        mainContorller.setMainGUI(mainGUI);
        mainGUI.setMainController(mainContorller);

        FileOperation fileOperation = new FileOperation();
        mainContorller.setFileOperation(fileOperation);
        fileOperation.setMainController(mainContorller);

        mainContorller.setSiteSettings(new SiteSettings());

        FindTime time = new FindTime();
        mainContorller.setTime(time);

        LoginGUI loginGUI = new LoginGUI();
        LoginController loginController = new LoginController();
        loginController.setLoginGUI(loginGUI);
        loginGUI.setLoginController(loginController);

        mainContorller.setLoginController(loginController);
        loginController.setMainController(mainContorller);

        mainGUI.showMessage(helloMSG);

//        new MyNotes();
//        new Window();
    } 
    public static void promt(String str){ 
        System.out.println(str); 
    } 
} 
