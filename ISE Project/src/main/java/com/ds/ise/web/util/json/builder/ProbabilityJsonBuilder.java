package com.ds.ise.web.util.json.builder;

import com.ds.ise.entity.Item;

public class ProbabilityJsonBuilder extends TripleValueArrayJsonBuilder<Item, Double> {

    public static final String ID_JSON_NAME = "id";
    public static final String PROBABILITY_JSON_NAME = "probability";
    public static final String ITEM_JSON_NAME = "item";

    @Override
    protected String getIdFromFulfillmentContainerMap(Item item) {
        return Long.toString(item.getId());
    }

    @Override
    protected String getKeyFromFulfillmentContainerMap(Item item) {
        return item.getName();
    }

    @Override
    protected String getValueFromFulfillmentContainerMap(Double probability) {
        return probability.toString();
    }

    @Override
    protected String getFirstParameterName() {
        return ID_JSON_NAME;
    }

    @Override
    protected String getSecondParameterName() {
        return PROBABILITY_JSON_NAME;
    }

    @Override
    protected String getThirdParameterName() {
        return ITEM_JSON_NAME;
    }
}
