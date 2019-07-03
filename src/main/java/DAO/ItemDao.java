package DAO;

import Entity.Item;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class ItemDao {

    private static ItemDao itemDao;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public static ItemDao instance(){
        if(itemDao==null){
            itemDao = new ItemDao();
        }
        return itemDao;
    }

    private ItemDao() {
    }

    public List<Item> getAll() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Item.class);
        return (List<Item>) criteria.list();
    }

    public void add (Item item){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();

    }
    }
//String name, String imgLink, double price