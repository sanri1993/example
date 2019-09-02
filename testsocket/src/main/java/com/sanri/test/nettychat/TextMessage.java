package com.sanri.test.nettychat;

public class TextMessage implements Message {
    private String value;

    public TextMessage(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public MessageType getType() {
        return MessageType.TEXT;
    }
}
