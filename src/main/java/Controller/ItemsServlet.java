package Controller;

import Factory.ServiceFactories.AccountServiceFactory;
import Factory.ServiceFactories.ItemServiceFactory;
import Service.Interfaces.AccountService;
import Service.Interfaces.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/items")
public class ItemsServlet extends HttpServlet {

    private final AccountService ACCOUNT_SERVICE = AccountServiceFactory.getAccountServiceImpl();
    private static final ItemService ITEM_SERVICE = ItemServiceFactory.getItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userName", ACCOUNT_SERVICE.getUser().getLogin());
        req.setAttribute("items", ITEM_SERVICE.getAll());
        req.getServletContext().getRequestDispatcher("/Items.jsp").forward(req, resp);
    }
}
