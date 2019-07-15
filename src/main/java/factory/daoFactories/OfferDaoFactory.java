package factory.daoFactories;

import dao.implementations.OfferDaoImpl;
import dao.interfaces.OfferDao;

public class OfferDaoFactory {

    private static OfferDao offerDao;

    public static OfferDao getInstance() {
        if (offerDao == null) {
            offerDao = new OfferDaoImpl();
        }
        return offerDao;
    }
}
