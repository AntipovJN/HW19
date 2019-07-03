package Service;

import Entity.User;
import DAO.UserDAO;

public class AccountService {

    private User user;
    private boolean isLogin;
    private static AccountService accountService;
    private UserDAO userDAO = UserDAO.instance();

    public static AccountService instance() {
        if (accountService == null) {
            accountService = new AccountService();
        }
        return accountService;
    }

    private AccountService() {
    }

    public boolean signIn(String login, String pass) {
        User userDB = UserDAO.instance().getUserByLogin(login);
        if (userDB != null) {
            if (userDB.getPassword().equals(pass)) {
                user = userDB;
                isLogin = true;
            }
        }
        return isLogin;
    }

    public boolean signUp(String login, String pass, String passwordRepeat) {
        if (pass.equals(passwordRepeat)) {
            return userDAO.addUser(login, pass);
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void endSession() {
        isLogin = false;
        user = null;
    }
}
