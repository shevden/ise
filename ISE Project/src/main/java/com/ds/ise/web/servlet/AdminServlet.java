package com.ds.ise.web.servlet;

import com.ds.ise.constant.PathConstant;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private static final int serialVersionUID = 1;

    private static final Logger LOG = Logger.getLogger(AdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("Do GET started.");
        LOG.debug("Do GET finished: forward on: " + PathConstant.PAGE_ADMIN);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PathConstant.PAGE_ADMIN);
        dispatcher.forward(request, response);
    }

}
