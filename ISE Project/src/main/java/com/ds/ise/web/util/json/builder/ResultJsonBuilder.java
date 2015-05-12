package com.ds.ise.web.util.json.builder;

import com.ds.ise.entity.Item;
import com.ds.ise.web.util.json.builder.JsonBuilder;

public class ResultJsonBuilder extends JsonBuilder {

    public void buildResultJson(Item result){
        appendBeginning();
        appendEscapedValue("title");
        appendColon();
        appendEscapedValue(result.getName());
        appendEnding();
    }
}
