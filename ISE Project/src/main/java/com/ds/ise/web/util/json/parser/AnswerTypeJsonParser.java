package com.ds.ise.web.util.json.parser;

import com.ds.ise.entity.additional.AnswerType;
import org.json.JSONObject;

import javax.ejb.Stateless;

@Stateless
public class AnswerTypeJsonParser {

    public static final String ANSWER_OPTION_CODE = "answerOptionCode";

    public AnswerType parseJson(String json){
        JSONObject jsonObject = new JSONObject(json);
        String answerOptionCode = jsonObject.getString(ANSWER_OPTION_CODE);
        try {
            int ordinal = Integer.parseInt(answerOptionCode);
            return AnswerType.getByOrdinal(ordinal);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Wrong answer option code specified.", e);
        }
    }
}
