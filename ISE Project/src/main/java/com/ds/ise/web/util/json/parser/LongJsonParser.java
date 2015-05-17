package com.ds.ise.web.util.json.parser;

import org.json.JSONObject;

import javax.ejb.Stateless;

@Stateless
public class LongJsonParser {

    public static final String LONG_NUMBER_CODE = "longValue";

    public Long parseJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String longValue = jsonObject.getString(LONG_NUMBER_CODE);
        try {
            return Long.parseLong(longValue);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Wrong data type specified.", e);
        }
    }

}
