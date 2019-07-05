package Factory.DaoFactories;

import dao.Implementations.UserDaoHibernateImpl;
import dao.Interfaces.UserDao;

public class UserDaoFactory {

    private static UserDao userDao;

    public static UserDao getUserDaoHibernateImpl() {
        if (userDao == null) {
            userDao = UserDaoHibernateImpl.instance();
        }
        return userDao;
    }
}
