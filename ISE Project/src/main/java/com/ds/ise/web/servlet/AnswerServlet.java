package com.ds.ise.web.servlet;

import com.ds.ise.constant.AttributeConstant;
import com.ds.ise.constant.PathConstant;
import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.entity.Item;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@WebServlet("/answer")
public class AnswerServlet extends HttpServlet {

    public static final String ITEM_ATTR = "item";

    private static final int serialVersionUID = 1;

    private static final Logger LOG = Logger.getLogger(AnswerServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("Do GET started.");
        HttpSession session = request.getSession();
        SessionDataContainer dataContainer = (SessionDataContainer)
                session.getAttribute(AttributeConstant.SESSION_DATA_CONTAINER);
        Item item = dataContainer.getResultItem();
        request.setAttribute(ITEM_ATTR, item);
        LOG.debug("Do GET finished: forward on: " + PathConstant.PAGE_ANSWER);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PathConstant.PAGE_ANSWER);
        dispatcher.forward(request, response);
    }
}
