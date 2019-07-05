package Service.Implementations;

import Entity.User;
import Factory.DaoFactories.UserDaoFactory;
import Service.Interfaces.AccountService;
import dao.Implementations.UserDaoHibernateImpl;
import dao.Interfaces.UserDao;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private User user;
    private boolean isLogin;
    private static AccountServiceImpl accountService;
    private UserDao userDao = UserDaoFactory.getUserDaoHibernateImpl();

    public static AccountServiceImpl instance() {
        if (accountService == null) {
            accountService = new AccountServiceImpl();
        }
        return accountService;
    }

    private AccountServiceImpl() {
    }

    @Override
    public List<User> getAll() {
        return userDao.getAllUsers();
    }

    @Override
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

    @Override
    public boolean signUp(String login, String pass, String passwordRepeat) {
        if (pass.equals(passwordRepeat)) {
            return userDao.addUser(login, pass);
        }
        return false;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public boolean isLogin() {
        return isLogin;
    }

    @Override
    public void endSession() {
        isLogin = false;
        user = null;
    }
}
