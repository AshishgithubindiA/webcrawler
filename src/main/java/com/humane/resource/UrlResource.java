package com.humane.resource;

import com.humane.WebCrawlerApplication;
import com.humane.enums.VisitedStatus;
import com.humane.model.UrlEntity;
import com.humane.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.validation.Valid;

/**
 * Created by ashish on 23/03/20.
 */
@RequestMapping(value = "scrap")
@RestController
public class UrlResource {

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    Queue queue;

    @RequestMapping(method = RequestMethod.POST)
    public String scrawlUrl(@RequestBody UrlEntity request) {
        request.setIsVisited(VisitedStatus.NOT_VISITED);
        jmsTemplate.convertAndSend("inmemory.queue", GsonUtil.getJson(request));
        WebCrawlerApplication.seedUrl = request.getUrlString();
        return request.getUrlString()+" scrapping in progress..";
    }
}
