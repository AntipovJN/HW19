package сontroller;

import factory.serviceFactories.ItemServiceFactory;
import factory.serviceFactories.SessionServiceFactory;
import services.interfaces.ItemService;
import services.interfaces.SessionService;
import utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/items")
public class ItemsServlet extends HttpServlet {

    private static final ItemService ITEM_SERVICE = ItemServiceFactory.getInstance();
    private static final SessionService SESSION_SERVICE = SessionServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (ResponseUtil.checkLoginResponse(req, resp)) {
            req.setAttribute("items", ITEM_SERVICE.getAll());
            req.setAttribute("isAdmin", SESSION_SERVICE.isAdmin(req));
            req.getServletContext().getRequestDispatcher("/Items.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("pokupka");
        }
    }
}
