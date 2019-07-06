package factory.daoFactories;

import dao.implementations.ItemDaoImpl;
import dao.interfaces.ItemDao;

public class ItemDaoFactory {

    private static ItemDao itemDao;

    public static ItemDao getItemDaoHibernateImpl() {
        if (itemDao == null) {
            itemDao = ItemDaoImpl.instance();
        }
        return itemDao;
    }
}
