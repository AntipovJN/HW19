package Service.Implementations;

import Entity.Item;
import Factory.DaoFactories.ItemDaoFactory;
import Service.Interfaces.ItemService;
import dao.Interfaces.ItemDao;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao = ItemDaoFactory.getItemDaoHibernateImpl();
    private static ItemServiceImpl itemService;

    public static ItemServiceImpl instance() {
        if (itemService == null) {
            itemService = new ItemServiceImpl();
        }
        return itemService;
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public void add(Item item) {
        itemDao.add(item);
    }
}
