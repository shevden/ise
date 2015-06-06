package com.ds.ise.service.processor;

import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.entity.additional.AnswerType;
import com.ds.ise.logic.calculator.ProbabilityCalculator;
import com.ds.ise.constant.AttributeConstant;
import com.ds.ise.web.util.json.parser.AnswerTypeJsonParser;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.http.HttpSession;

@Stateful
public class AnswerProcessor {

    @EJB
    private AnswerTypeJsonParser jsonParser;

    @EJB
    private ProbabilityCalculator probabilityCalculator;

    public void process(String input, HttpSession session){
        SessionDataContainer sessionDataContainer =
                (SessionDataContainer) session.getAttribute(AttributeConstant.SESSION_DATA_CONTAINER);
        AnswerType answer = jsonParser.parseJson(input);
        sessionDataContainer.addAnswerToQuestion(answer);
        probabilityCalculator.recalculateProbability(sessionDataContainer);
        sessionDataContainer.nullifyLastAskedQuestion();
    }
}
