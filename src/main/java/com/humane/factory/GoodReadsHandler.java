package com.humane.factory;

import com.humane.model.BookInfo;
import com.humane.repository.BookInfoRepository;
import com.humane.util.HtmlDocUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ashish on 23/03/20.
 */
@Component
public class GoodReadsHandler extends WebsiteHandler{
    public static String HTML_TD_BOOK_ITEMTYPE = "http://schema.org/Book";
    public static String HTML_ATTRIBUTE_TAG_TBODY = "tbody";
    public static String HTML_ATTRIBUTE_TAG_TR = "tr";
    public static String HTML_ATTRIBUTE_TAG_TD = "td";
    public static String HTML_ATTRIBUTE_ITEMPROP = "itemprop";
    public static String HTML_ATTRIBUTE_ITEMPROP_NAME = "name";
    public static String HTML_ATTRIBUTE_ITEMPROP_AUTHOR = "author";
    public static String HTML_ELEMENT_SPAN = "span";
    public static String HTML_ELEMENT_IMG = "img";

    private BookInfoRepository bookInfoRepository ;

    @Autowired
    public GoodReadsHandler(BookInfoRepository bookInfoRepository){
        this.bookInfoRepository = bookInfoRepository;
    }


    public void scrapData(Document document){
        Elements jpgs = document.select(HtmlDocUtil.HTML_ELEMENT_IMAGES_WITH_TAG_JPG);

        for (Element jpg : jpgs) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setBookName(jpg.attr("title"));
            bookInfo.setImageUrl(jpg.attr("src"));
            System.out.println("src: " + jpg.attr("src"));
            System.out.println("title: " + jpg.attr("title"));
            bookInfoRepository.save(bookInfo);
        }


    }

}
