package com.humane;

import com.humane.enums.VisitedStatus;
import com.humane.model.UrlEntity;
import com.humane.util.GsonUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.io.IOException;

/**
 * Created by ashish on 13/03/20.
 */
@ComponentScan({"com.humane.util"
        ,"com.humane.repository"
        ,"com.humane.service"
        ,"com.humane.listener"
        ,"com.humane.helper"})
@SpringBootApplication
@EnableJms
public class WebCrawlerApplication {

    public static String seedUrl = "https://www.goodreads.com/";
    public static void main(String[] args) {

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrlString(seedUrl);
        urlEntity.setIsVisited(VisitedStatus.NOT_VISITED);

        ConfigurableApplicationContext context = SpringApplication.run(WebCrawlerApplication.class, args);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend("inmemory.queue", GsonUtil.getJson(urlEntity));

    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("inmemory.queue");
    }
}
