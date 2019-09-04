package com.sanri.test.testrabbitmq;

import com.sanri.test.testrabbitmq.dto.SendBean;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRabbitmqApplicationTests {

    @Autowired
    ConnectionFactory  connectionFactory;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void send2Queue() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            SendBean sendBean = new SendBean(i, "hello" + i, new Date());
            rabbitTemplate.convertAndSend("test",sendBean);
        }
    }

    @Test
    public void send2Exchange(){
        final String [] testDirectExchange = {"info","error","warning"};
        for (int i = 0; i < 1000; i++) {
            String routingKey = testDirectExchange[RandomUtils.nextInt(0,3)];
            SendBean sendBean = new SendBean(i, routingKey+ i, new Date());
            rabbitTemplate.convertAndSend("direct_exchange",routingKey,sendBean);
        }
    }
}
