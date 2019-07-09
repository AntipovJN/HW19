package dao.interfaces;

import entity.Item;

import java.util.List;

public interface ItemDao {

    List<Item> getAll();

    void add(Item item);

    void remove(int id);

    Item getItemById(int id);

    void updateItem(Item item);
}
