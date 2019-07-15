package services.implementations;

import dao.interfaces.OfferDao;
import entity.Code;
import entity.Offer;
import factory.daoFactories.OfferDaoFactory;
import services.interfaces.OfferService;

import java.util.List;

public class OfferServiceImpl implements OfferService {

    private static final OfferDao offerDao = OfferDaoFactory.getInstance();

    @Override
    public List<Offer> getAll() {
        return offerDao.getAll();
    }

    @Override
    public void addOffer(String id, String listOfProducts, int sum, Code code) {
        offerDao.addOffer(new Offer(id, listOfProducts, sum, code));
    }

    @Override
    public void update(Offer offer) {
        offerDao.update(offer);
    }

    @Override
    public void removeOffer(Offer offer) {
        offerDao.removeOffer(offer);
    }

    @Override
    public Offer getOfferById(String id) {
        return offerDao.getOfferById(id);
    }
}
