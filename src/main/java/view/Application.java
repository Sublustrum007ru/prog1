package view;

import controller.*;
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

        ParsingSitesTest parsingSitesTest = new ParsingSitesTest();
        mainContorller.setParseSitesTest(parsingSitesTest);
        parsingSitesTest.setMainController(mainContorller);
        FindCategory findCategory = new FindCategory();
        FindProducts findProducts =new FindProducts();
        parsingSitesTest.setFindCategory(findCategory);
        parsingSitesTest.setFindProducts(findProducts);
        findCategory.setParsingSitesTest(parsingSitesTest);
        findProducts.setParsingSitesTest(parsingSitesTest);

        mainGUI.showMessage(helloMSG);

//        new MyNotes();
    }
    public static void promt(String str){ 
        System.out.println(str); 
    } 
} 
