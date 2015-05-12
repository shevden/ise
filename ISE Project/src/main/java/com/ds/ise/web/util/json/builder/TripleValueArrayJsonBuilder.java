package com.ds.ise.web.util.json.builder;

import java.util.Map;

public abstract class TripleValueArrayJsonBuilder<T, E> extends JsonBuilder {

    public static final String WRAPPER_JSON_NAME = "wrapper";

    public void buildTripleValueArrayJson(Map<T, E> valuesToFulfillWith) {
        appendTripleValueArrayBeginning();
        for (Map.Entry<T, E> questionEntropy : valuesToFulfillWith.entrySet()) {
            buildTripleValueArrayLine(questionEntropy);
        }
        truncateTripleValueArrayEnding();
        appendArrayEnding();
    }

    private void buildTripleValueArrayLine(Map.Entry<T, E> fulfillmentContainer) {
        String firstLinePart = getIdFromFulfillmentContainerMap(fulfillmentContainer.getKey());
        String secondLinePart = getValueFromFulfillmentContainerMap(fulfillmentContainer.getValue());
        String thirdLinePart = getKeyFromFulfillmentContainerMap(fulfillmentContainer.getKey());
        appendFirstLinePart(firstLinePart);
        appendSecondLinePart(secondLinePart);
        appendThirdLinePart(thirdLinePart);
    }

    protected abstract String getIdFromFulfillmentContainerMap(T t);

    protected abstract String getKeyFromFulfillmentContainerMap(T t);

    protected abstract String getValueFromFulfillmentContainerMap(E e);

    protected abstract String getFirstParameterName();

    protected abstract String getSecondParameterName();

    protected abstract String getThirdParameterName();

    private TripleValueArrayJsonBuilder<T, E> appendTripleValueArrayBeginning() {
        appendBeginning();
        appendEscapedValue(WRAPPER_JSON_NAME);
        appendColon();
        appendArrayBeginning();
        return this;
    }

    private TripleValueArrayJsonBuilder<T, E> appendFirstLinePart(String firstLineParameter) {
        appendBeginning();
        appendEscapedValue(getFirstParameterName());
        appendColon();
        appendEscapedValue(firstLineParameter);
        appendComma();
        return this;
    }

    private TripleValueArrayJsonBuilder<T, E> appendSecondLinePart(String secondLineParameter) {
        appendEscapedValue(getSecondParameterName());
        appendColon();
        appendEscapedValue(secondLineParameter);
        appendComma();
        return this;
    }

    private TripleValueArrayJsonBuilder<T, E> appendThirdLinePart(String thirdLineParameter) {
        appendEscapedValue(getThirdParameterName());
        appendColon();
        appendEscapedValue(thirdLineParameter);
        appendEnding();
        appendComma();
        return this;
    }

    private TripleValueArrayJsonBuilder<T, E> truncateTripleValueArrayEnding() {
        int lastCommaPosition = jsonBuilder.length() - 1;
        jsonBuilder.replace(lastCommaPosition, lastCommaPosition + 1, EMPTY);
        return this;
    }
}
