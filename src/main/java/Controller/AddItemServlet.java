package Controller;

import Factory.ServiceFactories.AccountServiceFactory;
import Factory.ServiceFactories.ItemServiceFactory;
import Service.Interfaces.AccountService;
import Service.Interfaces.ItemService;
import Entity.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/additem")
public class AddItemServlet extends HttpServlet {

    private static final AccountService ACCOUNT_SERVICE = AccountServiceFactory.getAccountServiceImpl();
    private static final ItemService ITEM_SERVICE = ItemServiceFactory.getItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!ACCOUNT_SERVICE.getUser().getLogin().equals("admin")) {
            resp.sendRedirect("/items");
            return;
        }
        req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
