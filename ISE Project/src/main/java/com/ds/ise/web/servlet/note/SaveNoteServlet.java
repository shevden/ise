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

@WebServlet("/saveNote")
public class SaveNoteServlet extends HttpServlet {

    public static final String NOTE_ID_PARAM = "id";
    public static final String NOTE_ATTR = "note";
    public static final String ORIGINAL_ID_PARAM = "originalId";
    public static final String ATTACHED_TO_PARAM = "attachedTo";
    public static final String TITLE_PARAM = "title";
    public static final String DESCRIPTION_PARAM = "description";

    private static final int serialVersionUID = 1;

    private static final Logger LOG = Logger.getLogger(SaveNoteServlet.class);

    @EJB
    private NoteDAO noteDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("Do GET started.");
        String idParam = request.getParameter(NOTE_ID_PARAM);
        if(idParam != null && !idParam.isEmpty()){
            long idToEdit = Long.parseLong(idParam);
            Note noteToEdit = noteDAO.find(idToEdit);
            request.setAttribute(NOTE_ATTR, noteToEdit);
        }
        LOG.debug("Do GET finished: forward on: " + PathConstant.PAGE_SAVE_NOTE);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PathConstant.PAGE_SAVE_NOTE);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("Do POST started.");
        String originalId = request.getParameter(ORIGINAL_ID_PARAM);
        String attachedTo = request.getParameter(ATTACHED_TO_PARAM);
        String title = request.getParameter(TITLE_PARAM);
        String description = request.getParameter(DESCRIPTION_PARAM);
        if(originalId != null && !originalId.isEmpty()){
            Note note = noteDAO.find(Long.parseLong(originalId));
            note.setTitle(title);
            note.setDescription(description);
            noteDAO.update(note);
        } else {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute(PathConstant.USER_ATTR_NAME);
            Note note = new Note();
            note.setTitle(title);
            note.setDescription(description);
            note.setUserId(user.getId());
            note.setItemId(0);
            if(attachedTo != null && !attachedTo.isEmpty()){
                note.setItemId(Long.parseLong(attachedTo));
            }
            noteDAO.save(note);
        }
        LOG.debug("Do POST finished: redirect on: " + PathConstant.REQUEST_NOTE_LIST);
        response.sendRedirect(PathConstant.REQUEST_NOTE_LIST);
    }
}
