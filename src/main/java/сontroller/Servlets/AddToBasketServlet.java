package сontroller.Servlets;

import factory.serviceFactories.BasketServiceFactory;
import factory.serviceFactories.UserServiceFactory;
import services.interfaces.BasketService;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addToBasket")
public class AddToBasketServlet extends javax.servlet.http.HttpServlet {

    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        basketService.addToBasket(id,req);
        resp.sendRedirect("/items");
    }
}
