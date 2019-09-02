package test;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 创建人     : sanri
 * 创建时间   : 2018/8/12-11:53
 * 功能       : 发送消息测试
 */
public class KafkaSendMsgTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("metadata.broker.list","localhost:9092");
        ProducerConfig producerConfig = new ProducerConfig(properties);
        Producer producer = new Producer(producerConfig);

        KeyedMessage<String,byte[]> keyedMessage = null;
        try {
            keyedMessage = new KeyedMessage<String,byte[]>("test",null,(System.currentTimeMillis()+"").getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        producer.send(keyedMessage);
    }
}
