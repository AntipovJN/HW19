package services.interfaces;

import entity.Code;
import entity.Offer;

import java.util.List;

public interface OfferService {

    List<Offer> getAll();

    void addOffer(String id, String listOfProducts, int sum, Code code);

    void update(Offer offer);

    void removeOffer(Offer offer);

    Offer getOfferById(String id);

}
