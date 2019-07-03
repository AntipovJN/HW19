package Controller;

import DAO.ItemDao;
import Entity.Item;
import Service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/additem")
public class AddItemServlet extends HttpServlet {

    private static final AccountService ACCOUNT_SERVICE = AccountService.instance();
    private static final ItemDao ITEM_DAO = ItemDao.instance();

    private boolean invalidData = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ACCOUNT_SERVICE.getUser().getLogin().equals("admin")) {
            resp.sendRedirect("/pokupka");
            return;
        }
        req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String imgLink = req.getParameter("img");
        double price = -1;
        if (!req.getParameter("price").isEmpty()) {
            price = Double.valueOf(req.getParameter("price"));
        }
        if (name.isEmpty() || imgLink.isEmpty() || price < 0) {
            req.setAttribute("isEmpty", "All fields must be initialized and price must be biggest than 0.");
            req.getServletContext().getRequestDispatcher("/AddItem.jsp").forward(req,resp);
        } else {
            ITEM_DAO.add(new Item(name, imgLink, price));
            resp.sendRedirect("/pokupka");
        }
    }
}
