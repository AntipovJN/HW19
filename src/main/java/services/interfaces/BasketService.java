package services.interfaces;

import entity.Item;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BasketService {

    void addToBasket(String id, HttpServletRequest request);

    void removeFromBasket(String id, HttpServletRequest request);

    boolean isEmptyBasket(HttpServletRequest request);

    List<Item> itemsList(HttpServletRequest request);

    int sum(HttpServletRequest request);
}
