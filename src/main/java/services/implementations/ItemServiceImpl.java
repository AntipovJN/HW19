package services.implementations;

import entity.Item;
import factory.daoFactories.ItemDaoFactory;
import services.interfaces.ItemService;
import dao.interfaces.ItemDao;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Objects;

public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao = ItemDaoFactory.getInstance();

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public void add(Item item) {
        itemDao.add(item);
    }

    @Override
    public void remove(int id) {
        itemDao.remove(id);
    }

    @Override
    public Item getItemById(int id) {
        return itemDao.getItemById(id);
    }

    @Override
    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    @Override
    public void updateItem(String name, String img, double price, Item oldItem) throws  AuthenticationException {
        if (Objects.isNull(name) || Objects.isNull(img) || price < 0 || Objects.isNull(oldItem)
                || name.isEmpty() || img.isEmpty()) {
            throw new AuthenticationException("Wrong data");
        }
        oldItem.setName(name);
        oldItem.setPrice(price);
        oldItem.setImg(img);
        updateItem(oldItem);
    }
}
