package com.ds.ise.service.processor;

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

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Stateful
public class StopSearchSessionProcessor {

    @EJB
    private LongJsonParser jsonParser;

    @EJB
    private ItemDAO itemDAO;

    @EJB
    private AnswerDAO answerDAO;

    @EJB
    private SessionDataContainerFactory sessionDataContainerFactory;

    public void process(String input, HttpSession session){
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
    }

}