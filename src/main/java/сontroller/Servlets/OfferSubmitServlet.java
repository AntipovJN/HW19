package сontroller.Servlets;

import entity.Code;
import entity.Offer;
import factory.serviceFactories.BasketServiceFactory;
import factory.serviceFactories.MailServiceFactory;
import factory.serviceFactories.OfferServiceFactory;
import factory.serviceFactories.UserServiceFactory;
import services.interfaces.BasketService;
import services.interfaces.MailService;
import services.interfaces.OfferService;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/offer")
public class OfferSubmitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();
    private static final MailService mailService = MailServiceFactory.getInstance();
    private static final OfferService offerService = OfferServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Code code = new Code(userService.getUserFromSession(req).get());
        offerService.addOffer(req.getParameter("id"),
                userService.getUserFromSession(req).get().getBasket(),
                basketService.sum(req), code);
        mailService.sendMessage(code, userService.getUserFromSession(req).get().getLogin());
        req.setAttribute("code", code.getCode());
        req.setAttribute("id", req.getParameter("id"));
        req.getServletContext().getRequestDispatcher("/OfferSubmit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Offer offer = offerService.getOfferById(req.getParameter("id"));
        if (req.getParameter("codeFromForm").equals(req.getParameter("code"))) {
             offer.setStatus("SUCCESS");
            offerService.update(offer);
            userService.getUserFromSession(req).get().setBasket("");
            userService.updateUser(userService.getUserFromSession(req).get());
            resp.sendRedirect("/pokupka");
        } else {
            offer.setStatus("NOT SUCCESS");
            offerService.update(offer);
            req.setAttribute("isInvalidCode", "Invalid code! Try again");
            req.getServletContext().getRequestDispatcher("/OfferSubmit.jsp").forward(req, resp);
        }
    }
}
