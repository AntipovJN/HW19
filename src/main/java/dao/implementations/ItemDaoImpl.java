package dao.implementations;

import dao.interfaces.ItemDao;
import entity.Item;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Item getItemById(int id) {
        if (id == 0) {
            return null;
        }
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Item WHERE id=:itemId");
        query.setInteger("itemId", id);
        return getUserByQuery(query, session);
    }

    @Override
    public void updateItem(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public void remove(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(getItemById(id));
        session.getTransaction().commit();
        session.close();
    }

    private Item getUserByQuery(Query query, Session session) {
        try {
            Item hibernateItem = (Item) query.iterate().next();
            Item item = new Item(hibernateItem.getName(), hibernateItem.getImg(), hibernateItem.getPrice());
            item.setId(hibernateItem.getId());
            item.setProductCode(hibernateItem.getProductCode());
            session.close();
            return item;
        } catch (NoSuchElementException ex) {
            return null;
        }
    }
}
