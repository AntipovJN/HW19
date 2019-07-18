package dao.implementations;

import dao.interfaces.ItemDao;
import entity.Item;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ItemDaoImpl implements ItemDao {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public List<Item> getAll() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Item.class);
        return (List<Item>) criteria.list();
    }

    public void add(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();

    }
}
