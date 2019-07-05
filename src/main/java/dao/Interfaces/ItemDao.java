package dao.Interfaces;

import Entity.Item;

import java.util.List;

public interface ItemDao {

    List<Item> getAll();

    void add(Item item);
}
