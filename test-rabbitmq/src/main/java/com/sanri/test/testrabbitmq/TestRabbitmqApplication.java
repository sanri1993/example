package com.sanri.test.testrabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class TestRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestRabbitmqApplication.class, args);
    }

}
