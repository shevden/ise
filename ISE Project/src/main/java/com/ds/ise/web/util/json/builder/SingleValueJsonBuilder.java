package com.ds.ise.web.util.json.builder;

public abstract class SingleValueJsonBuilder<T> extends JsonBuilder {

    public void buildSingleValueJson(T fulfillmentContainer){
        appendBeginning();
        appendEscapedValue(getParameterName());
        appendColon();
        appendEscapedValue(getValueFromFulfillmentContainer(fulfillmentContainer));
        appendEnding();
    }

    protected abstract String getParameterName();

    protected abstract String getValueFromFulfillmentContainer(T t);

}
