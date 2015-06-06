package com.ds.ise.service.resource;

import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.constant.AttributeConstant;
import com.ds.ise.web.util.json.converter.QuestionEntropyJsonConverter;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.http.HttpSession;

@Stateful
public class FulfillEntropyResource  {

    @EJB
    private QuestionEntropyJsonConverter questionEntropyJsonConverter;

    public JSONObject getResource(HttpSession session){
        SessionDataContainer sessionDataContainer =
                (SessionDataContainer) session.getAttribute(AttributeConstant.SESSION_DATA_CONTAINER);

        return questionEntropyJsonConverter.convert(sessionDataContainer.getQuestionEntropies());
    }
}
