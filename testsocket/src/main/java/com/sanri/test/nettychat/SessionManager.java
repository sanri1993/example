package com.sanri.test.nettychat;

import io.netty.channel.Channel;
import io.netty.util.internal.PlatformDependent;

import java.util.concurrent.ConcurrentMap;

public class SessionManager {
    // channel 管理器
//    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static final ConcurrentMap<String, ChatSession> userSessions = PlatformDependent.newConcurrentHashMap();

    public static ChatSession createSession(String userId,Channel channel){
        ChatSession chatSession = userSessions.get(userId);
        if(chatSession != null){
            return chatSession;
        }
        userSessions.putIfAbsent(userId,new ChatSession(userId,channel));
        return userSessions.get(userId);
    }
}
