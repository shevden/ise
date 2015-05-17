package com.ds.ise.web.util.json.merger;

import org.json.JSONObject;

import javax.ejb.Stateless;
import java.util.Collection;

@Stateless
public class JsonMerger {

    public JSONObject merge(Collection<JSONObject> jsons){
        JSONObject mergedJson = new JSONObject();
        for(JSONObject partialJson: jsons){
            processSingleJson(mergedJson, partialJson);
        }

        return mergedJson;
    }

    private void processSingleJson(JSONObject merged, JSONObject json) {
        for(String key: JSONObject.getNames(json)){
            merged.put(key, json.get(key));
        }
    }
}
