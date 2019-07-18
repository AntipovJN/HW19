package dao.interfaces;

import entity.Item;

import java.util.List;

public interface ItemDao {

    List<Item> getAll();

    void add(Item item);
}
