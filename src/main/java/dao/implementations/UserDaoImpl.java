package dao.implementations;

import dao.interfaces.UserDao;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import java.io.ObjectOutputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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
        if (Objects.isNull(login)) {
            return null;
        }
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE login=:userLogin");
        query.setString("userLogin", login);
        return getUserByQuery(query, session);
    }

    @Override
    public User getUserById(int id) {
        if (id == 0) {
            return null;
        }
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE id=:userId");
        query.setInteger("userId", id);
        return getUserByQuery(query, session);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        return (List<User>) criteria.list();
    }

    private User getUserByQuery(Query query, Session session) {
        try {
            User hibernateUser = (User) query.iterate().next();
            User user = new User(hibernateUser.getLogin(), hibernateUser.getPassword());
            user.setId(hibernateUser.getId());
            user.setAuthorized(hibernateUser.isAuthorized());
            user.setAuthorized(hibernateUser.isAuthorized());
            session.close();
            return user;
        } catch (NoSuchElementException ex) {
            return null;
        }
    }
}
