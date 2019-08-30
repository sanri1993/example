package com.sanri.test.testrabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRabbitmqApplicationTests {

    @Autowired
    ConnectionFactory  connectionFactory;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() throws InterruptedException {
        MessageProperties messageProperties = new MessageProperties();
        for (int i = 0; i < 1000; i++) {
//            Message message = new Message(("hello"+i).getBytes(),messageProperties);
//            rabbitTemplate.send("test",message);
            SendBean sendBean = new SendBean(i, "hello" + i, new Date());
            rabbitTemplate.convertAndSend("test",sendBean);
        }

    }

}
