package com.humane;

import com.humane.enums.VisitedStatus;
import com.humane.model.UrlEntity;
import com.humane.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.jms.Queue;
import java.io.IOException;

/**
 * Created by ashish on 13/03/20.
 */
@ComponentScan({"com.humane.util"
        ,"com.humane.repository"
        ,"com.humane.service"
        ,"com.humane.listener"
        ,"com.humane.helper"
        ,"com.humane.resource"
        ,"com.humane.factory"})
@SpringBootApplication
@EnableJms
@Configuration
@Slf4j
@EnableSwagger2
public class WebCrawlerApplication {

    public static String seedUrl;
    public static void main(String[] args) {
        SpringApplication.run(WebCrawlerApplication.class, args);

    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("inmemory.queue");
    }
}
