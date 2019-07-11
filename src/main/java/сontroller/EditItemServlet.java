package сontroller;

import entity.Item;
import factory.serviceFactories.ItemServiceFactory;
import services.interfaces.ItemService;
import utils.ResponseUtil;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/items/edit")
public class EditItemServlet extends HttpServlet {

    private static final ItemService ITEM_SERVICE = ItemServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        ResponseUtil.isAdminResponse(req, resp);
           try {
            Item item = ITEM_SERVICE.getItemById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("action", "/items/edit");
            req.setAttribute("name", item.getName());
            req.setAttribute("img", item.getImg());
            req.setAttribute("price", item.getPrice());
            req.getSession().setAttribute("id",req.getParameter("id"));
        } catch (Exception e) {
            resp.getWriter().println("Invalid id");
        }
        req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        ResponseUtil.isAdminResponse(req, resp);
        String name = req.getParameter("name");
        String img = req.getParameter("img");
        try {
            Item item = ITEM_SERVICE.getItemById((int) req.getSession().getAttribute("id"));
            req.getSession().removeAttribute("id");
            double price = Double.valueOf(req.getParameter("price"));
            ITEM_SERVICE.updateItem(name, img, price, item);
            resp.sendRedirect("/pokupka");
        } catch (AuthenticationException e) {
            req.setAttribute("isEmpty", e.getMessage());
            req.setAttribute("name", name);
            req.setAttribute("img",img);
            req.setAttribute("action", "/items/edit");
            req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req, resp);
        }
    }
}
