package com.sanri.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

public class KafkaConsumerMain {
    @Test
    public void testConsumer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "沙井坑货");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset","earliest");  //Java 默认是latest 以前的数据在启动时不加这句消费不到  latest, earliest, none
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
//        TopicPartition test = new TopicPartition("test", 0);
//        consumer.assign(Collections.singletonList(test));
        consumer.subscribe(Arrays.asList("test"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            Iterable<ConsumerRecord<String, String>> test1 = records.records("test");
            Iterator<ConsumerRecord<String, String>> iterator = test1.iterator();
            while (iterator.hasNext()) {
                ConsumerRecord<String, String> record = iterator.next();
                System.out.printf("offset = %d, key = %s, partition= %s, value = %s \n", record.offset(), record.key(), record.partition(),record.value());
            }
        }

    }
}
