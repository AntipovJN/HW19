package сontroller;

import factory.serviceFactories.ItemServiceFactory;
import services.interfaces.ItemService;
import utils.ResponseUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/items/remove")
public class RemoveItemsServlet extends HttpServlet {

    private static final ItemService ITEM_SERVICE = ItemServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (ResponseUtil.isAdminResponse(req, resp)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                ITEM_SERVICE.remove(id);
            } catch (NumberFormatException | NullPointerException e) {
                resp.getWriter().println("<h1>Invalid id</h1> \n <a href=\"/pokupka\"> Get Back </a>");
                return;
            }
        }
        resp.sendRedirect("/items");
    }
}
