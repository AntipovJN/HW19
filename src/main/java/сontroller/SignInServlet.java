package сontroller;

import factory.serviceFactories.UserServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import services.interfaces.UserService;
import utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class SignInServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(SignInServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ResponseUtil.checkNotLoginResponse(req, resp);
        req.setAttribute("process", "Sign in");
        req.setAttribute("action", "login");
        req.getServletContext().getRequestDispatcher("/Authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ResponseUtil.checkNotLoginResponse(req, resp);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (userService.signIn(login, password, req)) {
            logger.debug("User " + login + " was authorized");
            resp.sendRedirect("/pokupka");
        } else {
            req.setAttribute("isInvalid", "Invalid login or pass");
            req.setAttribute("process", "Sign In");
            req.setAttribute("action", "login");
            req.setAttribute("login", login);
            logger.info("Attempt to authorize with login:" + login + " ; Password:" + password
                    + " ; By " + req.getSession().getServletContext().getServerInfo());
            req.getServletContext().getRequestDispatcher("/Authorization.jsp").forward(req, resp);
        }
    }
}
