package factory.serviceFactories;

import services.implementations.BasketServiceImpl;

import services.interfaces.BasketService;

public class BasketServiceFactory {

    private static BasketService basketService;

    public static BasketService getInstance() {
        if (basketService == null) {
            basketService = new BasketServiceImpl();
        }
        return basketService;
    }
}
