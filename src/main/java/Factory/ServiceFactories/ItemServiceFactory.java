package Factory.ServiceFactories;

import Service.Interfaces.ItemService;
import Service.Implementations.ItemServiceImpl;

public class ItemServiceFactory {

    private static ItemService itemService;

    public static ItemService getItemServiceImpl() {
        if (itemService == null) {
            itemService = ItemServiceImpl.instance();
        }
        return itemService;
    }
}
