package com.ds.ise.web.util.json.builder;

import com.ds.ise.entity.Question;

public class EntropyJsonBuilder extends TripleValueArrayJsonBuilder<Question, Double> {

    public static final String ID_JSON_NAME = "id";
    public static final String ENTROPY_JSON_NAME = "entropy";
    public static final String QUESTION_JSON_NAME = "question";

    @Override
    protected String getIdFromFulfillmentContainerMap(Question question) {
        return Long.toString(question.getId());
    }

    @Override
    protected String getKeyFromFulfillmentContainerMap(Question question) {
        return question.getDescription();
    }

    @Override
    protected String getValueFromFulfillmentContainerMap(Double entropy) {
        return entropy.toString();
    }

    @Override
    protected String getFirstParameterName() {
        return ID_JSON_NAME;
    }

    @Override
    protected String getSecondParameterName() {
        return ENTROPY_JSON_NAME;
    }

    @Override
    protected String getThirdParameterName() {
        return QUESTION_JSON_NAME;
    }
}
