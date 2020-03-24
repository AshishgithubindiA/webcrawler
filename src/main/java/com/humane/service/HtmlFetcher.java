package com.humane.service;

import com.humane.enums.VisitedStatus;
import com.humane.exception.JsoupHandleException;
import com.humane.factory.HandlerFactory;
import com.humane.factory.WebsiteHandler;
import com.humane.model.UrlEntity;
import com.humane.repository.UrlRepository;
import com.humane.util.JsoupUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.io.IOException;

/**
 * Created by ashish on 13/03/20.
 */
@Component
public class HtmlFetcher {
//    private JsoupUtil jsoupUtil;
    private UrlRepository repository;
    private UrlExtractor urlExtractor;
    private WebsiteHandler handler;
    private HandlerFactory handlerFactory;


    @Autowired
    public HtmlFetcher( UrlRepository repository
            , UrlExtractor urlExtractor
            , HandlerFactory handlerFactory){
//        this.jsoupUtil = jsoupUtil;
        this.repository = repository;
        this.urlExtractor = urlExtractor;
        this.handlerFactory = handlerFactory;

    }

    public void downloadDocument(UrlEntity urlEntity) {
        Document document = null;
        if(urlEntity.getIsVisited()==VisitedStatus.INVALID_URL){
            throw new JsoupHandleException("Invalid Url: "+urlEntity.getUrlString());
        }

        handler = handlerFactory.getHandler(urlEntity.getUrlString());

        try {
            document = handler.fetchDocument(urlEntity.getUrlString());
        } catch (JsoupHandleException e) {
            urlEntity.setIsVisited(VisitedStatus.INVALID_URL);
            repository.save(urlEntity);
            throw new JsoupHandleException("Invalid Url: "+urlEntity.getUrlString(),e);
        } catch (IOException e) {
            urlEntity.setIsVisited(VisitedStatus.INVALID_URL);
            repository.save(urlEntity);
            throw new JsoupHandleException("Not able to connect: "+urlEntity.getUrlString(),e);
        }
        urlEntity.setIsVisited(VisitedStatus.VISITED);
        repository.save(urlEntity);
        if(document==null) {
            System.out.println("No document downloaded for :" + urlEntity.toString());
            return;
        }

        processDocument(document);

    }


    public void processDocument(Document document){
//        urlExtractor = new UrlExtractor(handler);
        urlExtractor.getUrlsFromDoc(document,handler);
    }
}
