package services.interfaces;

import entity.Item;

import javax.naming.AuthenticationException;
import java.util.List;

public interface ItemService {

    List<Item> getAll();

    void add(Item item);

    void remove(int id);

    Item getItemById(int id);

    void updateItem(Item item);

    void updateItem(String name, String img, double price, Item oldItem)
            throws  AuthenticationException;
}
