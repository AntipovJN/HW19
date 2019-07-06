package factory.daoFactories;

import dao.implementations.UserDaoImpl;
import dao.interfaces.UserDao;

public class UserDaoFactory {

    private static UserDao userDao;

    public static UserDao getUserDaoHibernateImpl() {
        if (userDao == null) {
            userDao = UserDaoImpl.instance();
        }
        return userDao;
    }
}
