package com.ds.ise.web.util.json.converter;

import com.ds.ise.entity.Question;
import org.json.JSONObject;

import javax.ejb.Stateless;

@Stateless
public class QuestionJsonConverter {

    public static final String JSON_FIELD_DESCRIPTION = "description";

    public JSONObject convert(Question question){
        JSONObject result = new JSONObject();
        result.put(JSON_FIELD_DESCRIPTION, question.getDescription());

        return result;
    }
}
