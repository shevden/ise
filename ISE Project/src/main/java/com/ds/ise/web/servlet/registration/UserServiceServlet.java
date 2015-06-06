package com.ds.ise.web.servlet.registration;

import com.ds.ise.service.user.UserService;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Provides registration page on the AJAX GET requests and executes
 * specified functions from the {@code UserService} class.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@WebServlet("/userServiceHelper")
public class UserServiceServlet extends HttpServlet {

    public static final String EMAIL_PARAM_NAME = "email";

    private static final Logger LOG = Logger.getLogger(UserServiceServlet.class);

    @EJB
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("Do GET started.");
        String email = request.getParameter(EMAIL_PARAM_NAME);
        LOG.trace("Parameter received: " + EMAIL_PARAM_NAME + " = " + email);
        response.setContentType("text/plain");
        if(userService.doesExist(email)){
            response.getWriter().write("This email is already registered.");
        }
        LOG.debug("Do GET finished.");
    }
}
