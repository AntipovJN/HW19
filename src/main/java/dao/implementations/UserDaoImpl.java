package dao.implementations;

import dao.interfaces.UserDao;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.NoSuchElementException;

public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public boolean addUser(String login, String password) {
        if (getUserByLogin(login) == null) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new User(login, password));
            session.getTransaction().commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE login=:userLogin");
        query.setString("userLogin", login);
        try {
            User hibernateUser = (User) query.iterate().next();
            User user = new User(hibernateUser.getLogin(), hibernateUser.getPassword());
            session.close();
            return user;
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        return (List<User>) criteria.list();
    }
}
