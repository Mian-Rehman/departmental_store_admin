package com.rehman.newtrends.Chat;

public class ChatListModel
{
    String chatKey;
    String chatType;
    String message;
    String senderUID;

    public ChatListModel(String chatKey, String chatType, String message, String senderUID) {
        this.chatKey = chatKey;
        this.chatType = chatType;
        this.message = message;
        this.senderUID = senderUID;
    }

    public ChatListModel() {
    }

    public String getChatKey() {
        return chatKey;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderUID() {
        return senderUID;
    }

    public void setSenderUID(String senderUID) {
        this.senderUID = senderUID;
    }
}
