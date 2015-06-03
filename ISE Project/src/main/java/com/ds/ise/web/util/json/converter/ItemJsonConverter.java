package com.ds.ise.web.util.json.converter;

import com.ds.ise.entity.Item;
import org.json.JSONObject;

import javax.ejb.Stateless;

@Stateless
public class ItemJsonConverter {

    public static final String JSON_KEY_TITLE = "title";
    public static final String JSON_KEY_GENRE = "genre";
    public static final String JSON_KEY_PUBLISHER = "publisher";
    public static final String JSON_KEY_DEVELOPER = "developer";
    public static final String JSON_KEY_RELEASE_DATE = "releaseDate";
    public static final String JSON_KEY_DESCRIPTION = "description";
    public static final String JSON_KEY_COVER_PATH = "coverPath";

    public JSONObject convert(Item item){
        JSONObject result = new JSONObject();
        result.put(JSON_KEY_TITLE, item.getName());
        result.put(JSON_KEY_GENRE, item.getGenre());
        result.put(JSON_KEY_PUBLISHER, item.getPublisher());
        result.put(JSON_KEY_DEVELOPER, item.getDeveloper());
        result.put(JSON_KEY_RELEASE_DATE, item.getReleaseDate());
        result.put(JSON_KEY_DESCRIPTION, item.getDescription());
        result.put(JSON_KEY_COVER_PATH, item.getCoverPath());

        return result;
    }
}
