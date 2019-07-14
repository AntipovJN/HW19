package сontroller;

import factory.serviceFactories.UserServiceFactory;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/pokupka", loadOnStartup = 1)
public class MainPageServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (userService.isLogin(req)) {
            req.getServletContext().getRequestDispatcher("/LoginMain.jsp").forward(req, resp);
        } else {
            req.getServletContext().getRequestDispatcher("/Main.jsp").forward(req, resp);
        }
    }
}
