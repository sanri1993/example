package com.sanri.test.testrabbitmq.configs;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        //统一处理 confirm 回调,应该统一消息
//        rabbitTemplate.setConfirmCallback();
        return rabbitTemplate;
    }

    // 交换机声明
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_exchange");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_exchange");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic_exchange");
    }

    //声明一些队列
    @Bean
    public Queue testQueue(){
        return new Queue("test",true);
    }

    @Bean("common")
    public Queue commonQueue(){
        return new Queue("common_queue",false);
    }

    @Bean("error")
    public Queue errorQueue(){
        return new Queue("error_queue",true);
    }


    //绑定队列到交换机上
    @Bean("errbinding")
    public Binding errbinding(){
        return BindingBuilder.bind(errorQueue()).to(directExchange()).with("error");
    }

    @Bean("infoBinding")
    public Binding infoBinding(){
        return  BindingBuilder.bind(commonQueue()).to(directExchange()).with("info");
    }
    @Bean("warningBinding")
    public Binding warningBinding(){
        return BindingBuilder.bind(commonQueue()).to(directExchange()).with("warning");
    }
}

