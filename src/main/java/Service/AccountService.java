package Service;

import Entity.User;
import dao.Implementations.UserDaoHibernateImpl;
import dao.Interfaces.UserDao;

public class AccountService {

    private User user;
    private boolean isLogin;
    private static AccountService accountService;
    private UserDao userDao = UserDaoHibernateImpl.instance();

    public static AccountService instance() {
        if (accountService == null) {
            accountService = new AccountService();
        }
        return accountService;
    }

    private AccountService() {
    }

    public boolean signIn(String login, String pass) {
        User userDB = UserDaoHibernateImpl.instance().getUserByLogin(login);
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
            return userDao.addUser(login, pass);
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
