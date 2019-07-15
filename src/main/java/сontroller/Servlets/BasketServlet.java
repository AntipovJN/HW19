package сontroller.Servlets;

import factory.serviceFactories.BasketServiceFactory;
import factory.serviceFactories.UserServiceFactory;
import services.interfaces.BasketService;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/user/basket")
public class BasketServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
      if(basketService.itemsList(req).isEmpty()) {
          req.setAttribute("isEmptyBasket",
                  "Your basket is empty, please add at least 1 product");
      } else {
          req.setAttribute("items", basketService.itemsList(req));
          String id = new StringBuilder().append(userService.getUserFromSession(req).get().getId())
                  .append(new Date().getTime()).toString();
          req.setAttribute("id", id);
      }
      req.getServletContext().getRequestDispatcher("/Basket.jsp").forward(req,resp);
    }
}
