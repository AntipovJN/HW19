package dao.implementations;

import dao.interfaces.ItemDao;
import entity.Item;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ItemDaoImpl implements ItemDao {

    private static ItemDaoImpl itemDao;

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public static ItemDaoImpl instance() {
        if (itemDao == null) {
            itemDao = new ItemDaoImpl();
        }
        return itemDao;
    }

    private ItemDaoImpl() {
    }

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
