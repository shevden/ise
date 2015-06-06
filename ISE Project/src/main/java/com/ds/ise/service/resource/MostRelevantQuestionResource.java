package com.ds.ise.service.resource;

import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.entity.Question;
import com.ds.ise.logic.calculator.EntropyCalculator;
import com.ds.ise.constant.AttributeConstant;
import com.ds.ise.web.util.json.converter.QuestionJsonConverter;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.http.HttpSession;

@Stateful
public class MostRelevantQuestionResource {

    @EJB
    private EntropyCalculator entropyCalculator;

    @EJB
    private QuestionJsonConverter questionJsonConverter;

    public JSONObject getResource(HttpSession session) {
        SessionDataContainer sessionDataContainer =
                (SessionDataContainer) session.getAttribute(AttributeConstant.SESSION_DATA_CONTAINER);
        entropyCalculator.recalculateEntropy(sessionDataContainer);
        Question mostRelevantQuestion = entropyCalculator.getMinEntropyQuestion(sessionDataContainer);
        sessionDataContainer.setLastAskedQuestion(mostRelevantQuestion);
        sessionDataContainer.removeIndexedQuestion(mostRelevantQuestion);

        return questionJsonConverter.convert(mostRelevantQuestion);
    }
}
