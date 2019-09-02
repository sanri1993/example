package com.sanri;

import kafka.admin.DeleteTopicCommand;
import kafka.admin.TopicCommand;

/**
 * 创建人     : sanri
 * 创建时间   : 2018/8/12-14:02
 * 功能       : 使用 kafka 自带的 TopicCommand 操作主题数据
 */
public class MetaManager {
    private String connectString;

    public  MetaManager(String connectString){
        this.connectString = connectString;
    }

    /**
     * 创建主题
     * @param topic 主题名称
     * @param partitions 分区数
     * @param replication 副本数
     */
    public void createTopic(String topic,int partitions,int replication){
        String[] options = {"--create" ,
                "--zookeeper" ,connectString,
                "--partitions",partitions+"",
                "--topic",topic,
                "--replication-factor",replication+""};
        TopicCommand.main(options);
    }

    public void listTopics(){
        String[] options = {"--list",
                            "--zookeeper" ,connectString};
        TopicCommand.main(options);
    }

    public void descTopics(String topic) {
        String[] options = new String[]{"--describe",
                "--zookeeper", connectString,
                "--topic", topic};
        TopicCommand.main(options);
    }

    public void deleteTopic(String topic){
        String [] options = {
                "--zookeeper", connectString,
                "--topic",topic
        };
        DeleteTopicCommand.main(options);
    }
}
