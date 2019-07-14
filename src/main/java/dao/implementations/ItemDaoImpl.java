package dao.implementations;

import dao.interfaces.ItemDao;
import entity.Item;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.NoSuchElementException;

public class ItemDaoImpl implements ItemDao {

    private static final Logger logger = Logger.getRootLogger();
    private static final Logger daoItemLogger = Logger.getLogger(ItemDaoImpl.class);
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public List<Item> getAll() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Item.class);
        return (List<Item>) criteria.list();
    }

    @Override
    public void add(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        daoItemLogger.info("Was add new Item:" + item.getName() + " ID:" + item.getId());
    }

    @Override
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
        daoItemLogger.info("Was update Item:" + item.getName() + " ID:" + item.getId());
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(getItemById(id));
        session.getTransaction().commit();
        session.close();
        daoItemLogger.info("Was remove Item ID:" + id);
    }

    private Item getUserByQuery(Query query, Session session) {
        try {
            Item temporaryItem = (Item) query.iterate().next();
            Item item = new Item(temporaryItem.getName()
                    , temporaryItem.getImg(), temporaryItem.getPrice());
            item.setId(temporaryItem.getId());
            item.setProductCode(temporaryItem.getProductCode());
            session.close();
            return item;
        } catch (NoSuchElementException ex) {
            logger.error(ex.getMessage() + " " + ex.getStackTrace());
            return null;
        }
    }
}
