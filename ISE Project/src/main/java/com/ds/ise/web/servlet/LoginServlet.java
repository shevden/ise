package com.ds.ise.web.servlet;

import com.ds.ise.constant.PathConstant;
import com.ds.ise.entity.User;
import com.ds.ise.exception.login.LoginException;
import com.ds.ise.service.user.UserService;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Provides login page access on the GET requests and processes
 * logging in of the particular {@code User} on the POST requests.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public static final String EMAIL_PARAM_NAME = "email";
    public static final String PASSWORD_PARAM_NAME = "password";
    public static final String LOGIN_ERROR_MESSAGE_PARAM_NAME = "loginErrorMessage";
    public static final String REFERRER_HEADER_NAME = "Referer";

    private static final int serialVersionUID = 1;

    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    @EJB
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        LOG.trace("Do POST started.");
        String email = request.getParameter(EMAIL_PARAM_NAME);
        String password = request.getParameter(PASSWORD_PARAM_NAME);
        LOG.trace("Email to login: " + email);
        LOG.trace("Password to login: " + password);
        User user;
        HttpSession session = request.getSession(true);
        String currentURL = getCurrentURL(request);
        try {
            user = userService.getAuthorizedUser(email, password);
        } catch (LoginException ex) {
            processLoginException(currentURL, ex, response);
            return;
        }
        LOG.debug("User to login: " + user);
        session.setAttribute(PathConstant.USER_ATTR_NAME, user);
        LOG.trace("Do POST finished, redirect to: " + currentURL);
        response.sendRedirect(currentURL);
    }

    private String getCurrentURL(HttpServletRequest request){
        String currentURL = (String) request.getSession().getAttribute(PathConstant.CURRENT_URL_PATH_ATTR_NAME);
        if (currentURL == null) {
            currentURL = PathConstant.REQUEST_CLIENT;
        }
        String referrer = request.getHeader(REFERRER_HEADER_NAME);
        if(referrer != null) {
            currentURL = referrer;
        }

        return currentURL;
    }

    private void processLoginException(String currentURL, LoginException ex,
            HttpServletResponse response) throws IOException {
        LOG.info("Failed to login: " + ex.getMessage());
        if(!currentURL.contains("?")){
            currentURL += "?";
        } else {
            currentURL += "&";
        }
        currentURL += LOGIN_ERROR_MESSAGE_PARAM_NAME + "=" + ex.getMessage();
        LOG.trace("Do POST finished, redirect to: " + currentURL);
        response.sendRedirect(currentURL);
    }

}
