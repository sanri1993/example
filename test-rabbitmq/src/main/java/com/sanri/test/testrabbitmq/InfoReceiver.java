package com.sanri.test.testrabbitmq;

import com.sanri.test.testrabbitmq.dto.SendBean;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class InfoReceiver {
    /**
     * @param sendBean
     */
    @RabbitListener(queues = "test")
    public void simple(SendBean sendBean){
        System.out.println("test:"+sendBean);
    }

    @RabbitListener(queues = "common_queue")
    public void commonQueue(SendBean sendBean) {
        System.out.println("common:"+sendBean);
    }

    @RabbitListener(queues = "error_queue")
    public void errorQueue(SendBean sendBean) {
        System.out.println("error:"+sendBean);
    }
}
