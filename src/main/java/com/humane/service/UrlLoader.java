package com.humane.service;

import com.humane.WebCrawlerApplication;
import com.humane.enums.VisitedStatus;
import com.humane.exception.JmsHandleException;
import com.humane.model.UrlEntity;
import com.humane.repository.UrlRepository;
import com.humane.util.GsonUtil;
import com.humane.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.jms.JMSException;
import javax.jms.Queue;
import java.util.Collections;
import java.util.List;

/**
 * Created by ashish on 14/03/20.
 */
@Component
public class UrlLoader {

    private UrlRepository repository;

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Queue queue;

    public UrlLoader(UrlRepository repository, JmsTemplate jmsTemplate, Queue queue){
        this.repository = repository;
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    public void loadUrl(List<String> subUrls) {
//        List<UrlEntity> allUrls = repository.findAll();
        for(String url: subUrls){
            if(!UrlUtil.isValidUrl(url)) continue;
            url = url.charAt(0)=='/'? WebCrawlerApplication.seedUrl+url:url;
            List<UrlEntity> urlEntities = repository.findByUrlString(url);

            if(CollectionUtils.isEmpty(urlEntities)){
                sendNewUrl(url);
            }else if(urlEntities.get(0).getIsVisited().equals(VisitedStatus.NOT_VISITED)){
                try{
                    jmsTemplate.convertAndSend(queue,GsonUtil.getJson(urlEntities.get(0)));
                }catch (JmsException e){
                    e.printStackTrace();
                }

            }else {
                System.out.println("Already visited : "+url);
            }

        }
    }

    public void sendNewUrl(String url) {
        UrlEntity urlEntity = new UrlEntity();
        url = !url.contains("http") || !url.contains("www.")? WebCrawlerApplication.seedUrl+url:url;
        urlEntity.setIsVisited(VisitedStatus.NOT_VISITED);
        urlEntity.setUrlString(url);
        repository.save(urlEntity);

        try{
            jmsTemplate.convertAndSend(queue, GsonUtil.getJson(urlEntity));
        }catch (JmsException e){
            e.printStackTrace();
        }

    }

}
