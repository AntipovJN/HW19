package factory.serviceFactories;

import services.implementations.OfferServiceImpl;
import services.interfaces.OfferService;

public class OfferServiceFactory {

    private static OfferService offerService;

    public static OfferService getInstance() {
        if (offerService == null) {
            offerService = new OfferServiceImpl();
        }
        return offerService;
    }
}
