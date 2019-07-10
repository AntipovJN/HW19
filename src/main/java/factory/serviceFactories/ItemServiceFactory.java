package factory.serviceFactories;

import services.interfaces.ItemService;
import services.implementations.ItemServiceImpl;

public class ItemServiceFactory {

    private static ItemService itemService;

    public static ItemService getInstance() {
        if (itemService == null) {
            itemService = new  ItemServiceImpl();
        }
        return itemService;
    }
}
