package com.sanri.test.nettychat;

import io.netty.channel.Channel;

/**
 * 聊天 session
 */
public class ChatSession {
    private Channel channel;
    private String userId;

    public ChatSession(String userId,Channel channel) {
        this.userId = userId;
        this.channel = channel;
    }

    /**
     * 写入消息到通道
     * @param message
     */
    public void write(Message message){
        String value = message.getValue();
        channel.writeAndFlush(value);
    }
}
