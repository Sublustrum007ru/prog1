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
        loginGUI.setLoginField("");
        loginGUI.setPassEntry("");
        loginGUI.setCheckUser("");
        loginGUI.setVisible(true);
    }

    public void clickLoginBtn(){
        if(checkLogin()){
            if(checkPasswd()){
                loginGUI.setCheckUser("Succesfull");
                startTimer(1000, loginGUI.getLogin());
                mainController.setVisbleLogonBtn(true);
            }else{
                loginGUI.setCheckUser("Password incorrect");
            }
        }else{
            loginGUI.setCheckUser("Login incorrect");
        }
    }
    private boolean checkLogin(){
        String login = loginGUI.getLogin();
        return login.equals(superUserLogin);
    }

    private boolean checkPasswd(){
        String password = loginGUI.getPassword();
        return password.equals(superUserPassword);
    }

    public void clickCancelBtn(){
        loginGUI.setVisible(false);
    }

    public void startTimer(int time, String nameUser) {
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                loginGUI.setVisible(false);
                mainController.message("Добро пожаловать в систему, " + nameUser + "!!!!");
            }
        };
        timer.schedule(task1, time);
    }


}
