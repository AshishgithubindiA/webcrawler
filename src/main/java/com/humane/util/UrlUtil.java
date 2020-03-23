package com.humane.util;

/**
 * Created by ashish on 21/03/20.
 */
public class UrlUtil {
    public static boolean isValidUrl(String url){
        if (url.length()<=1 || url.contains("jpg"))
            return false;

        return true;
    }
}
