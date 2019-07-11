package сontroller.Servlets;

import factory.serviceFactories.SessionServiceFactory;
import factory.serviceFactories.ItemServiceFactory;
import services.interfaces.ItemService;
import entity.Item;
import services.interfaces.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/additem")
public class AddItemServlet extends HttpServlet {

    private static final ItemService ITEM_SERVICE = ItemServiceFactory.getInstance();
    private static final SessionService SESSION_SERVICE = SessionServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("action", "/additem");
        req.setAttribute("process","Add item");
        if (!SESSION_SERVICE.isAdmin(req)) {
            resp.sendRedirect("/items");
        }
        req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!SESSION_SERVICE.isAdmin(req)) {
            resp.sendRedirect("/items");
        }
        String name = req.getParameter("name");
        String imgLink = req.getParameter("img");
        double price = -1;
        if (!req.getParameter("price").isEmpty()) {
            price = Double.valueOf(req.getParameter("price"));
        }
        if (name.isEmpty() || imgLink.isEmpty() || price < 0) {
            req.setAttribute("isEmpty",
                    "All fields must be initialized and price must be biggest than 0.");
            req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req, resp);
        } else {
            ITEM_SERVICE.add(new Item(name, imgLink, price));
            resp.sendRedirect("/items");
        }
    }
}
