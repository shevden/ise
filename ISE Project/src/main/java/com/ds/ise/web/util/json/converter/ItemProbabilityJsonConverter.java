package com.ds.ise.web.util.json.converter;

import com.ds.ise.entity.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class ItemProbabilityJsonConverter {

    public static final String ID = "id";
    public static final String PROBABILITY = "probability";
    public static final String NAME = "name";
    public static final String ITEM_PROBABILITY_FULFILLMENT = "itemProbabilityFulfillment";

    public JSONObject convert(Map<Item, Double> itemProbabilities){
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<Item, Double> itemProbability : itemProbabilities.entrySet()) {
            Item item = itemProbability.getKey();
            Double probability = itemProbability.getValue();
            JSONObject element = new JSONObject();
            element.put(ID, item.getId());
            element.put(PROBABILITY, probability);
            element.put(NAME, item.getName());
            jsonArray.put(element);
        }
        result.put(ITEM_PROBABILITY_FULFILLMENT, jsonArray);

        return result;
    }
}
