package services.implementations;

import entity.Item;
import factory.serviceFactories.ItemServiceFactory;
import factory.serviceFactories.UserServiceFactory;
import services.interfaces.BasketService;
import services.interfaces.ItemService;
import services.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class BasketServiceImpl implements BasketService {

    private UserService userService = UserServiceFactory.getInstance();
    private ItemService itemService = ItemServiceFactory.getInstance();

    @Override
    public void addToBasket(String id, HttpServletRequest request) {
        userService.getUserFromSession(request).get().setBasket(
                userService.getUserFromSession(request).get().getBasket() + id + ";");
        userService.updateUser(userService.getUserFromSession(request).get());
    }

    @Override
    public void removeFromBasket(String id, HttpServletRequest request) {

    }

    @Override
    public boolean isEmptyBasket(HttpServletRequest request) {
        return userService.getUserFromSession(request).get().getBasket().isEmpty();
    }

    @Override
    public List<Item> itemsList(HttpServletRequest request) {
        String basketString = userService.getUserFromSession(request).get().getBasket();
        List<Item> basket = new LinkedList<>();
        if (!basketString.isEmpty()) {
            for (String item : Arrays.asList(basketString.split(";"))) {
                basket.add(itemService.getItemById(Integer.valueOf(item)));
            }
            basket.sort(Comparator.comparing(Item::getProductCode));
        }
        return basket;
    }

    public int sum(HttpServletRequest request) {
        int i = 0;
        for (Item item : itemsList(request)) {
            i += item.getPrice();
        }
        return i;
    }
}
