package com.humane.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.humane.model.UrlEntity;

/**
 * Created by ashish on 21/03/20.
 */
public class GsonUtil {

    public static Gson gson = new Gson();

    public static UrlEntity getObject(String objectString){
        return gson.fromJson(objectString, UrlEntity.class);
    }

    public static String getJson(UrlEntity urlEntity){
        return gson.toJson(urlEntity);
    }
}
