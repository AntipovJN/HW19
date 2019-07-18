package services.implementations;

import entity.Item;
import factory.daoFactories.ItemDaoFactory;
import services.interfaces.ItemService;
import dao.interfaces.ItemDao;

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
