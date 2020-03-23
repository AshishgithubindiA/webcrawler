package com.humane.util;

import org.springframework.stereotype.Component;
import sun.java2d.pipe.SolidTextRenderer;

/**
 * Created by ashish on 23/03/20.
 */
@Component
public class HtmlDocUtil {
    public static String HTML_ELEMENT_TAG_P = "p";
    public static String HTML_ATTRIBUTE_HREF = "href";
    public static String HTML_ELEMENT_TAG_LINKS = "a[href]";

    public static String HTML_ELEMENT_IMAGES_WITH_TAG_PNG = "img[src$=.png]";
    public static String HTML_ELEMENT_IMAGES_WITH_TAG_JPG = "img[src$=.jpg]";

}
