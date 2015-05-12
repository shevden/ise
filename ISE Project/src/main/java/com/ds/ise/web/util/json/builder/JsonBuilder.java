package com.ds.ise.web.util.json.builder;

import java.io.Serializable;

public abstract class JsonBuilder implements Serializable {

    public static final String COLON =":";
    public static final String COMMA = ",";
    public static final String EMPTY = "";
    public static final String OPEN_BRACE = "{";
    public static final String CLOSE_BRACE ="}";
    public static final String OPEN_SQUARE_BRACE = "[";
    public static final String CLOSE_SQUARE_BRACE = "]";
    public static final String ESCAPED_QUOTES = "\"";

    protected final StringBuilder jsonBuilder;

    public JsonBuilder(){
        jsonBuilder = new StringBuilder();
    }

    @Override
    public String toString() {
        return jsonBuilder.toString();
    }

    protected JsonBuilder appendBeginning(){
        jsonBuilder.append(OPEN_BRACE);
        return this;
    }

    protected JsonBuilder appendArrayBeginning(){
        jsonBuilder.append(OPEN_SQUARE_BRACE);
        return this;
    }

    protected JsonBuilder appendColon(){
        jsonBuilder.append(COLON);
        return this;
    }

    protected JsonBuilder appendComma(){
        jsonBuilder.append(COMMA);
        return this;
    }

    protected JsonBuilder appendEscapedValue(String value){
        jsonBuilder.append(ESCAPED_QUOTES);
        jsonBuilder.append(value);
        jsonBuilder.append(ESCAPED_QUOTES);
        return this;
    }

    protected JsonBuilder appendEnding(){
        jsonBuilder.append(CLOSE_BRACE);
        return this;
    }

    protected JsonBuilder appendArrayEnding(){
        jsonBuilder.append(CLOSE_SQUARE_BRACE + CLOSE_BRACE);
        return this;
    }
}
