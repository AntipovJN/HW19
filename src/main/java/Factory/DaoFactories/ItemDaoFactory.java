package Factory.DaoFactories;

import dao.Implementations.ItemDaoHibernateImpl;
import dao.Interfaces.ItemDao;

public class ItemDaoFactory {

    private static ItemDao itemDao;

    public static ItemDao getItemDaoHibernateImpl() {
        if (itemDao == null) {
            itemDao = ItemDaoHibernateImpl.instance();
        }
        return itemDao;
    }
}
