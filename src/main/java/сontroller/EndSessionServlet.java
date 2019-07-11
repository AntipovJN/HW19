package сontroller;

import factory.serviceFactories.UserServiceFactory;
import services.interfaces.UserService;
import utils.ResponseUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/exit")
public class EndSessionServlet extends HttpServlet {

    private static final UserService USER_SERVICE = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        if (ResponseUtil.checkLoginResponse(req, resp)) {
            USER_SERVICE.endSession(req);
        }
        resp.sendRedirect("/pokupka");
    }
}
