package com.humane.listener;

import com.humane.model.UrlEntity;
import com.humane.service.HtmlFetcher;
import com.humane.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by ashish on 20/03/20.
 */
@Component
public class UrlFrontierListener {

    private HtmlFetcher htmlFetcher;

    @Autowired
    public UrlFrontierListener(HtmlFetcher htmlFetcher){
        this.htmlFetcher = htmlFetcher;
    }

    @JmsListener(destination = "inmemory.queue")
    public void listener(String urlMessage) {
        System.out.println("Received : "+ urlMessage.toString());
        htmlFetcher.downloadDocument(GsonUtil.getObject(urlMessage));
    }
}
