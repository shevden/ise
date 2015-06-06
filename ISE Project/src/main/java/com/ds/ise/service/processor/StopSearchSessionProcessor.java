package com.ds.ise.service.processor;

import com.ds.ise.data.dao.AnswerDAO;
import com.ds.ise.data.dao.ItemDAO;
import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.data.session.SessionDataContainerFactory;
import com.ds.ise.entity.Answer;
import com.ds.ise.entity.Item;
import com.ds.ise.entity.Question;
import com.ds.ise.entity.additional.AnswerType;
import com.ds.ise.constant.AttributeConstant;
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
        Long itemId;
        if(input == null || input.isEmpty()){
            itemId = sessionDataContainer.getResultItem().getId();
            sessionDataContainer.setResultItem(null);
        } else {
            itemId = jsonParser.parseJson(input);
        }
        for (Item item : sessionDataContainer.getRepositoryContainer().getItems()) {
            if (item.getId() == itemId) {
                item.incrementRequests();
                itemDAO.update(item);
                processBoundedAnswers(sessionDataContainer, item);
                sessionDataContainerFactory.prepareForReuse(sessionDataContainer);
                return;
            }
        }
    }

    private void processBoundedAnswers(SessionDataContainer sessionDataContainer, Item item) {
        Map<Item, Answer> iterable;
        Answer updateTarget;
        for (Map.Entry<Question, AnswerType> entry : sessionDataContainer.getQuestionsAnswers().entrySet()) {
            iterable = sessionDataContainer.getRepositoryContainer().getAnswers().get(entry.getKey());
            updateTarget = iterable.get(item);
            updateTarget.incrementAsks();
            updateTarget.incrementOptionRequestsValue(entry.getValue());
            answerDAO.update(updateTarget);
        }
    }

}
