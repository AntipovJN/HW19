package factory.daoFactories;

import dao.implementations.UserDaoImpl;
import dao.interfaces.UserDao;

public class UserDaoFactory {

    private static UserDao userDao;

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }
}
