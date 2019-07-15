package dao.implementations;

import dao.interfaces.OfferDao;
import entity.Offer;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class OfferDaoImpl implements OfferDao {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private static final Logger logger = Logger.getRootLogger();

   public List<Offer> getAll() {
       Session session = sessionFactory.openSession();
       Criteria criteria = session.createCriteria(Offer.class);
       return (List<Offer>)criteria.list();
   }

    public void addOffer(Offer offer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(offer);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Offer offer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(offer);
        session.getTransaction().commit();
        session.close();
    }

    public void removeOffer(Offer offer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(offer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Offer getOfferById(String id) {
        if(Objects.isNull(id)) {
            return null;
        }
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Offer WHERE id=:offerId");
        query.setString("offerId", id);
        return getOfferByQuery(query,session);
    }

    private Offer getOfferByQuery(Query query, Session session) {
        try {
            Offer intermediateOffer = (Offer) query.iterate().next();
            Offer offer = new Offer();
            offer.setId(intermediateOffer.getId());
            offer.setListOfProducts(intermediateOffer.getListOfProducts());
            offer.setStatus(intermediateOffer.getStatus());
            offer.setSum(intermediateOffer.getSum());
            offer.setUserId(intermediateOffer.getUserId());
            offer.setCode(intermediateOffer.getCode());
            session.close();
            return offer;
        } catch (NoSuchElementException ex) {
            logger.error(ex.getMessage() + " " + ex.getStackTrace());
            return null;
        }
    }
}
