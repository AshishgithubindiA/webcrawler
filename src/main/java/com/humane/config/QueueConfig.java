package com.humane.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * Created by ashish on 20/03/20.
 */

public class QueueConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("inmemory.queue");
    }
}
