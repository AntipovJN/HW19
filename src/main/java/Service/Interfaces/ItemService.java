package Service.Interfaces;

import Entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAll();

    void add(Item item);
}
