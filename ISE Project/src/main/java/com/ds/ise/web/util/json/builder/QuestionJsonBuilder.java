package com.ds.ise.web.util.json.builder;


import com.ds.ise.entity.Question;

public class QuestionJsonBuilder extends SingleValueJsonBuilder<Question> {

    public static final String DESCRIPTION_JSON_NAME = "description";

    @Override
    protected String getParameterName() {
        return DESCRIPTION_JSON_NAME;
    }

    @Override
    protected String getValueFromFulfillmentContainer(Question question) {
        return question.getDescription();
    }
}
