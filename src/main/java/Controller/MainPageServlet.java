package Controller;

import DAO.ItemDao;
import Service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/pokupka", loadOnStartup = 1)
public class MainPageServlet extends HttpServlet {
    private static final AccountService ACCOUNT_SERVICE = AccountService.instance();
    private static final ItemDao ITEM_DAO = ItemDao.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ACCOUNT_SERVICE.isLogin()) {
            req.setAttribute("userName", ACCOUNT_SERVICE.getUser().getLogin());
            req.setAttribute("items",ITEM_DAO.getAll());
            req.getServletContext().getRequestDispatcher("/LoginMain.jsp").forward(req, resp);

        } else {
            req.getServletContext().getRequestDispatcher("/Main.jsp").forward(req, resp);
        }
    }

}
