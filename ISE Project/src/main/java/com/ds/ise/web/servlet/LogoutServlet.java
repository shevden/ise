package com.ds.ise.web.servlet;


import com.ds.ise.constant.PathConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Provides logout of the particular {@code User} on the POST requests.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final int serialVersionUID = 1;

    private static final Logger LOG = Logger.getLogger(LogoutServlet.class);

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        LOG.trace("Do POST started.");
        HttpSession session = request.getSession();
        session.invalidate();
        LOG.trace("Do POST finished.");
        response.sendRedirect(PathConstant.REQUEST_CLIENT);
    }
}
