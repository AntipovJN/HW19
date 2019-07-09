package сontroller;

import factory.serviceFactories.SessionServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/items")
public class ItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (SessionServiceFactory.getInstance().isLogin(req)) {
            req.getServletContext().getRequestDispatcher("/Items.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/pokupka");
        }
    }
}
