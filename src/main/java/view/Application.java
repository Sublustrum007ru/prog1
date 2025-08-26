package view;

import controller.*;
import controller.finds.FindBaseUrl;
import controller.impl.FileOperation;


public class Application{
    public Application(){
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

        ParsingSites parsingSites = new ParsingSites();
        mainContorller.setParseSites(parsingSites);
        parsingSites.setMainController(mainContorller);
        FindBaseUrl findBaseUrl = new FindBaseUrl();
        parsingSites.setFindBaseURL(findBaseUrl);
        findBaseUrl.setParseSites(parsingSites);

        mainGUI.showMessage(helloMSG);

//        new MyNotes();
    }
    public static void promt(String str){ 
        System.out.println(str); 
    } 
} 
