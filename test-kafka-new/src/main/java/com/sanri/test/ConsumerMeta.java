package com.sanri.test;

import kafka.common.OffsetAndMetadata;
import kafka.coordinator.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

public class ConsumerMeta {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset","earliest");  //Java 默认是latest 以前的数据在启动时不加这句消费不到  latest, earliest, none
        props.put("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        KafkaConsumer<byte[], byte[]> consumer = new KafkaConsumer<byte[], byte[]>(props);
        TopicPartition test = new TopicPartition("__consumer_offsets", 48);
        consumer.assign(Collections.singletonList(test));
//        consumer.subscribe(Arrays.asList("__consumer_offsets"));

        while (true) {
            ConsumerRecords<byte[], byte[]> consumerRecords = consumer.poll(100);
            Iterable<ConsumerRecord<byte[], byte[]>> consumer_offsets = consumerRecords.records("__consumer_offsets");
            Iterator<ConsumerRecord<byte[], byte[]>> iterator = consumer_offsets.iterator();
            while (iterator.hasNext()){
                ConsumerRecord<byte[], byte[]> consumerRecord = iterator.next();

                ByteBuffer keyBuffer = ByteBuffer.wrap(consumerRecord.key());
                BaseKey baseKey = GroupMetadataManager.readMessageKey(keyBuffer);
                if(baseKey instanceof OffsetKey){
                    GroupTopicPartition groupTopicPartition = (GroupTopicPartition) baseKey.key();

                    String group = groupTopicPartition.group();
                    String topic = groupTopicPartition.topicPartition().topic();
                    int partition = groupTopicPartition.topicPartition().partition();

                    //获取 offset value
                    OffsetAndMetadata offsetAndMetadata = GroupMetadataManager.readOffsetMessageValue(ByteBuffer.wrap(consumerRecord.value()));
                    long offset = offsetAndMetadata.offset();

                    System.out.println("group:["+group+"],topic:["+topic+"],partition:["+partition+"],offset:["+offset+"]");

                }else if(baseKey instanceof GroupMetadataKey){
                    System.out.println("---------"+baseKey);
                    GroupMetadata groupMetadata = GroupMetadataManager.readGroupMessageValue(((GroupMetadataKey) baseKey).key(), ByteBuffer.wrap(consumerRecord.value()));
                    System.out.println(groupMetadata);
                }
            }
        }
    }
}
