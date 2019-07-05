package Controller;

import Service.Implementations.AccountServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/exit")
public class EndSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (AccountServiceImpl.instance().isLogin()) {
            AccountServiceImpl.instance().endSession();
        }
        resp.sendRedirect("/pokupka");
    }
}
