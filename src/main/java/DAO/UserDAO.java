package DAO;

import Entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import java.util.NoSuchElementException;

public class UserDAO {

    private static UserDAO userDAO;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public static UserDAO instance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    private UserDAO() {
    }

    public boolean addUser(String login, String password) {
        if ((login == null) || (password == null) || (login.equals("")) || (password.equals(""))) {
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

    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE login='" + login + "'");
        try {
        User u = (User) query.iterate().next();
        User user = new User (u.getLogin(),u.getPassword());
        session.close();
           return user;
       }catch (NoSuchElementException ex) {
           return null;
       }

    }
}
