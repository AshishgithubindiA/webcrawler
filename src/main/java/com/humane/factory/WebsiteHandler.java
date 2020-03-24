package com.humane.factory;

import com.humane.exception.JsoupHandleException;
import com.humane.util.HtmlDocUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashish on 23/03/20.
 */
@Component
@Primary
public class WebsiteHandler {



    public Document fetchDocument(String url) throws JsoupHandleException, IOException {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;

    }

    public List<String> extractUrls(Document document){
        Elements links = document.select(HtmlDocUtil.HTML_ELEMENT_TAG_LINKS);
        List<String> list = new ArrayList<String>();

        for (Element link : links) {
            list.add(link.attr(HtmlDocUtil.HTML_ATTRIBUTE_HREF));
            System.out.println("link : " + link.attr(HtmlDocUtil.HTML_ATTRIBUTE_HREF));
            System.out.println("text : " + link.text());
        }
        return list;
    }

    public List<String> extractJpgs(Document document) throws JsoupHandleException{
        Elements jpgs = document.select(HtmlDocUtil.HTML_ELEMENT_IMAGES_WITH_TAG_JPG);
        List<String> list = new ArrayList<String>();

        for (Element jpg : jpgs) {
            System.out.println("Name: " + jpg.attr("name"));
            System.out.println("src: " + jpg.attr("src"));
            list.add(jpg.attr("src"));
        }
        return list;
    }

    public List<String> extractPngs(Document document) throws JsoupHandleException{
        Elements pngs = document.select(HtmlDocUtil.HTML_ELEMENT_IMAGES_WITH_TAG_PNG);
        List<String> list = new ArrayList<String>();

        for (Element png : pngs) {
            System.out.println("Name: " + png.attr("name"));
            System.out.println("src: " + png.attr("src"));
            list.add(png.attr("src"));
        }
        return list;
    }

    public void scrapData(Document document) throws JsoupHandleException{

    }
}
