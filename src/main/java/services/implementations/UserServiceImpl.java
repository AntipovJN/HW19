package services.implementations;

import entity.User;
import factory.daoFactories.UserDaoFactory;
import services.interfaces.UserService;
import dao.interfaces.UserDao;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private User user;
    private boolean isLogin;
    private static UserServiceImpl accountService;
    private UserDao userDao = UserDaoFactory.getUserDaoHibernateImpl();

    public static UserServiceImpl instance() {
        if (accountService == null) {
            accountService = new UserServiceImpl();
        }
        return accountService;
    }

    private UserServiceImpl() {
    }

    @Override
    public List<User> getAll() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean signIn(String login, String pass) {
        User userDB = userDao.getUserByLogin(login);
        if (userDB != null) {
            if (userDB.getPassword().equals(pass)) {
                user = userDB;
                isLogin = true;
            }
        }
        return isLogin;
    }

    @Override
    public boolean signUp(String login, String password, String passwordRepeat) {
        if ((Objects.isNull(login)) || Objects.isNull(password) || Objects.isNull(passwordRepeat)
                || (login.isEmpty()) || (password.isEmpty()) || (passwordRepeat.isEmpty())) {
            return false;
        }
        if (password.equals(passwordRepeat)) {
            return userDao.addUser(login, password);
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
