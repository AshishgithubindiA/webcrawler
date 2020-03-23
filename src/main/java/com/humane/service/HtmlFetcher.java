package com.humane.service;

import com.humane.enums.VisitedStatus;
import com.humane.model.UrlEntity;
import com.humane.repository.UrlRepository;
import com.humane.util.JsoupUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ashish on 13/03/20.
 */
@Component
public class HtmlFetcher {
    private JsoupUtil jsoupUtil;
    private UrlRepository repository;
    private UrlExtractor urlExtractor;


    @Autowired
    public HtmlFetcher(JsoupUtil jsoupUtil, UrlRepository repository, UrlExtractor urlExtractor){
        this.jsoupUtil = jsoupUtil;
        this.repository = repository;
        this.urlExtractor = urlExtractor;

    }

    public void downloadDocument(UrlEntity urlEntity){
        Document document = jsoupUtil.fetchDocument(urlEntity.getUrlString());
        urlEntity.setIsVisited(VisitedStatus.VISITED);
        repository.save(urlEntity);
        if(document==null) {
            System.out.println("No document downloaded for :" + urlEntity.toString());
            return;
        }

        processDocument(document);

    }

    public void processDocument(Document document){
        urlExtractor.getUrlsFromDoc(document);
    }
}
