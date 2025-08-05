package controller;

import view.LoginGUI;

import java.util.Timer;
import java.util.TimerTask;

public class LoginController {

    private LoginGUI loginGUI;
    private MainController mainController;
    private final Timer timer = new Timer();

    private final String superUserLogin = "admin";
    private final String superUserPassword = "123456";

    public void setLoginGUI(LoginGUI loginGUI){
        this.loginGUI = loginGUI;
    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void showMessage(String message){
        mainController.message(message);
    }

    public void setVisibleLoginGUI(){
        loginGUI.setLoginField();
        loginGUI.setPassEntry();
        loginGUI.setVisible(true);
    }

    public void clickLoginBtn(){
        String login, password;
        login = loginGUI.getLogin();
        password = loginGUI.getPassword();
        if(login.equals(superUserLogin)){
            if(password.equals(superUserPassword)){
                loginGUI.setCheckUser("Succesfull");
                startTimer(1000, login);
            }else{
                loginGUI.setCheckUser("Password incorrect");
            }
        }else{
            loginGUI.setCheckUser("Login incorrect");
        }
    }

    public void clickCancelBtn(){
        loginGUI.setVisible(false);
    }

    public void startTimer(int time, String nameUser) {
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                loginGUI.setVisible(false);
                mainController.message("Добро пожаловать в систему " + nameUser + "!!!!");
            }
        };
        timer.schedule(task1, time);
    }


}
