package dao.interfaces;

import entity.Offer;

import java.util.List;

public interface OfferDao {

    List<Offer> getAll();

    void addOffer(Offer offer);

    void update(Offer offer);

    void removeOffer(Offer offer);

    Offer getOfferById(String id);

}
