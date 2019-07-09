package сontroller;

import dao.interfaces.ItemDao;
import entity.Item;
import factory.daoFactories.ItemDaoFactory;
import factory.serviceFactories.SessionServiceFactory;
import services.interfaces.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/items/change")
public class ChangeItemServlet extends HttpServlet {


    private static final SessionService SESSION_SERVICE = SessionServiceFactory.getInstance();
    private static final ItemDao ITEM_DAO = ItemDaoFactory.getItemDaoHibernateImpl();
    private Item item;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (SESSION_SERVICE.isAdmin(req)) {
            try {
                item = ITEM_DAO.getItemById(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("action", "/items    n    /change");
                req.setAttribute("name", item.getName());
                req.setAttribute("img", item.getImg());
                req.setAttribute("price", item.getPrice());
            } catch (Exception e) {
                resp.getWriter().println("Invalid id");
            }
            req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/pokupka");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (SESSION_SERVICE.isAdmin(req)) {
            String name = req.getParameter("name");
            String img = req.getParameter("img");
            Double price = Double.valueOf(req.getParameter("price"));
            if (!name.isEmpty() && !img.isEmpty() && price > 0) {
                item.setName(name);
                item.setImg(img);
                item.setPrice(price);
                ITEM_DAO.updateItem(item);
            } else {
                req.setAttribute("isEmpty", "Invalid data");
                req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req, resp);
                return;
            }
        }
        resp.sendRedirect("/pokupka");
    }
}

