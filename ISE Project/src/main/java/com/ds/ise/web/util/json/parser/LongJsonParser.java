package com.ds.ise.web.util.json.parser;

import org.json.JSONObject;

import javax.ejb.Stateless;

@Stateless
public class LongJsonParser implements JsonParser<Long> {

    public static final String INTEGER_NUMBER_CODE = "i";

    @Override
    public Long parseJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String longValue = jsonObject.getString(INTEGER_NUMBER_CODE);
        try {
            return Long.parseLong(longValue);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Wrong data type specified.", e);
        }
    }

}
