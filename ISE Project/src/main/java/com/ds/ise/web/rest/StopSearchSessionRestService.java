package com.ds.ise.web.rest;

import com.ds.ise.data.dao.AnswerDAO;
import com.ds.ise.data.dao.ItemDAO;
import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.data.session.SessionDataContainerFactory;
import com.ds.ise.entity.Answer;
import com.ds.ise.entity.Item;
import com.ds.ise.entity.Question;
import com.ds.ise.entity.additional.AnswerType;
import com.ds.ise.web.constant.AttributeConstant;
import com.ds.ise.web.util.json.parser.LongJsonParser;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@ManagedBean
@Path("/jsr/stopSession")
public class StopSearchSessionRestService {

    @EJB
    private LongJsonParser jsonParser;

    @EJB
    private ItemDAO itemDAO;

    @EJB
    private AnswerDAO answerDAO;

    @EJB
    private SessionDataContainerFactory sessionDataContainerFactory;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response jsonRest4(String input, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionDataContainer sessionDataContainer =
                (SessionDataContainer) session.getAttribute(AttributeConstant.SESSION_DATA_CONTAINER);
        Long itemId = jsonParser.parseJson(input);
        for (Item item : sessionDataContainer.getRepositoryContainer().getItems()) {
            if (item.getId() == itemId) {
                item.incrementRequests();
                itemDAO.update(item);
                Map<Item, Answer> iterable;
                Answer updateTarget;
                for (Map.Entry<Question, AnswerType> entry : sessionDataContainer.getQuestionsAnswers().entrySet()) {
                    iterable = sessionDataContainer.getRepositoryContainer().getAnswers().get(entry.getKey());
                    updateTarget = iterable.get(item);
                    updateTarget.incrementAsks();
                    updateTarget.incrementOptionRequestsValue(entry.getValue());
                    answerDAO.update(updateTarget);
                }
                break;
            }
        }
        sessionDataContainerFactory.prepareForReuse(sessionDataContainer);

        return Response.status(200).build();
    }
}
