package com.ds.ise.web.servlet.note;

import com.ds.ise.constant.PathConstant;
import com.ds.ise.data.dao.NoteDAO;
import com.ds.ise.entity.Note;
import com.ds.ise.entity.User;
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
import java.util.List;

@WebServlet("/noteList")
public class NoteListServlet extends HttpServlet {

    public static final  String NOTE_LIST_ATTR = "noteList";

    private static final int serialVersionUID = 1;

    private static final Logger LOG = Logger.getLogger(NoteListServlet.class);

    @EJB
    private NoteDAO noteDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("Do GET started.");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(PathConstant.USER_ATTR_NAME);
        List<Note> notes = noteDAO.findByUserId(user.getId());
        request.setAttribute(NOTE_LIST_ATTR, notes);
        LOG.debug("Do GET finished: forward on: " + PathConstant.PAGE_NOTE_LIST);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PathConstant.PAGE_NOTE_LIST);
        dispatcher.forward(request, response);
    }
}
