package services.interfaces;

import entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAll();

    void add(Item item);

    void remove(int id);
}
