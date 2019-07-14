package factory.daoFactories;

import dao.implementations.ItemDaoImpl;
import dao.interfaces.ItemDao;

public class ItemDaoFactory {

    private static ItemDao itemDao;

    public static ItemDao getInstance() {
        if (itemDao == null) {
            itemDao = new ItemDaoImpl();
        }
        return itemDao;
    }
}
