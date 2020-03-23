package com.humane.listener;

import com.humane.model.UrlEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by ashish on 20/03/20.
 */

@Component
public interface Consumer {

    @JmsListener(destination = "inmemory.queue")
    public void listener(UrlEntity message);
}
