package com.ds.ise.web.servlet.note;

import com.ds.ise.constant.PathConstant;
import com.ds.ise.data.dao.ItemDAO;
import com.ds.ise.data.dao.NoteDAO;
import com.ds.ise.entity.Item;
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

@WebServlet("/note")
public class NoteServlet extends HttpServlet {

    public static final String NOTE_ID_PARAM = "id";
    public static final String NOTE_ATTR = "note";
    public static final String ITEM_ATTR = "item";

    private static final int serialVersionUID = 1;

    private static final Logger LOG = Logger.getLogger(NoteServlet.class);

    @EJB
    private NoteDAO noteDAO;

    @EJB
    private ItemDAO itemDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("Do GET started.");
        long id = Long.parseLong(request.getParameter(NOTE_ID_PARAM));
        Note note = noteDAO.find(id);
        request.setAttribute(NOTE_ATTR, note);
        if(note.getItemId() == 0){
            LOG.debug("Do GET finished: forward on: " + PathConstant.PAGE_PLAIN_NOTE);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PathConstant.PAGE_PLAIN_NOTE);
            dispatcher.forward(request, response);
        } else {
            Item item = itemDAO.find(note.getItemId());
            request.setAttribute(ITEM_ATTR, item);
            LOG.debug("Do GET finished: forward on: " + PathConstant.PAGE_ATTACHED_NOTE);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PathConstant.PAGE_ATTACHED_NOTE);
            dispatcher.forward(request, response);
        }
    }
}
