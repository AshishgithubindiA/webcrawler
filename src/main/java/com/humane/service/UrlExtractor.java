package com.humane.service;

import com.humane.factory.GoodReadsHandler;
import com.humane.factory.WebsiteHandler;
import com.humane.model.BookInfo;
import com.humane.repository.BookInfoRepository;
import com.humane.util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ashish on 14/03/20.
 */
@Component
public class UrlExtractor {

    private JsoupUtil jsoupUtil;
    @Autowired
    private UrlLoader urlLoader;
    private BookInfoRepository bookInfoRepository;

    private WebsiteHandler handler;

    @Autowired
    public UrlExtractor(WebsiteHandler handler, BookInfoRepository bookInfoRepository){
        this.handler = handler;
        this.bookInfoRepository = bookInfoRepository;
    }

    public void getUrlsFromDoc(Document document, WebsiteHandler handler) {
        scrapData(document,handler);

        List<String> subUrls =  handler.extractUrls(document);
        List<String> jpgList =  handler.extractJpgs(document);
        List<String> pngList =  handler.extractPngs(document);
        urlLoader.loadUrl(subUrls);
    }

    public void scrapData(Document document, WebsiteHandler handler){
        handler.scrapData(document);
        List<BookInfo> allBooks = bookInfoRepository.findAll();
    }

    

}
