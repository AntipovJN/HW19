package factory.serviceFactories;

import services.interfaces.ItemService;
import services.implementations.ItemServiceImpl;

public class ItemServiceFactory {

    private static ItemService itemService;

    public static ItemService getItemServiceImpl() {
        if (itemService == null) {
            itemService = ItemServiceImpl.instance();
        }
        return itemService;
    }
}
