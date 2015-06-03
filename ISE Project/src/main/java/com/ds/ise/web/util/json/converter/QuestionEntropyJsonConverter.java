package com.ds.ise.web.util.json.converter;

import com.ds.ise.entity.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class QuestionEntropyJsonConverter {

    public static final String ID = "id";
    public static final String ENTROPY = "entropy";
    public static final String DESCRIPTION = "description";
    public static final String QUESTION_ENTROPY_FULFILLMENT = "questionEntropyFulfillment";

    public JSONObject convert(Map<Question, Double> questionEntropies){
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<Question, Double> questionEntropy : questionEntropies.entrySet()) {
            Question question = questionEntropy.getKey();
            Double entropy = questionEntropy.getValue();
            JSONObject element = new JSONObject();
            element.put(ID, question.getId());
            element.put(ENTROPY, String.format("%.7f", entropy));
            element.put(DESCRIPTION, question.getDescription());
            jsonArray.put(element);
        }
        result.put(QUESTION_ENTROPY_FULFILLMENT, jsonArray);

        return result;
    }

}
