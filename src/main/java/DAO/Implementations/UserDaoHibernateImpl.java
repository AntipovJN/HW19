package DAO.Implementations;

import DAO.Interfaces.UserDao;
import Entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class UserDaoHibernateImpl implements UserDao {

    private static UserDaoHibernateImpl userDAO;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public static UserDaoHibernateImpl instance() {
        if (userDAO == null) {
            userDAO = new UserDaoHibernateImpl();
        }
        return userDAO;
    }

    private UserDaoHibernateImpl() {
    }

    @Override
    public boolean addUser(String login, String password) {
        if ((Objects.isNull(login)) || Objects.isNull(password) || (login.isEmpty()) || (password.isEmpty())) {
            return false;
        }
        if(getUserByLogin(login)==null) {
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
        Query query = session.createQuery("from User where login=:userLogin");
        query.setString("userLogin",login);
        try {
        User hibernateUser = (User) query.iterate().next();
        User user = new User (hibernateUser.getLogin(),hibernateUser.getPassword());
        session.close();
           return user;
       }catch (NoSuchElementException ex) {
           return null;
       }

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        return (List<User>)criteria.list();
    }
}
