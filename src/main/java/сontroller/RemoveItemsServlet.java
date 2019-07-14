package сontroller;

import factory.serviceFactories.ItemServiceFactory;
import services.interfaces.ItemService;
import utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/items/remove")
public class RemoveItemsServlet extends HttpServlet {

    private static final ItemService itemService = ItemServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ResponseUtil.isAdminResponse(req, resp);
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            itemService.remove(id);
            resp.sendRedirect("/items");
        } catch (NumberFormatException | NullPointerException e) {
            req.getServletContext().getRequestDispatcher("/EditErrorPage.jsp").forward(req,resp);
        }
    }
}
