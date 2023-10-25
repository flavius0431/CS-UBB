package com.example.map_toysocialnetwork.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message extends Entity<Long> {
    private Long senderID;
    private Long receiverID;
    private String content;
    private LocalDateTime dateSent;

    public Long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Long receiverID) {
        this.receiverID = receiverID;
    }

    public Message(){}
    public Message(Long  senderID, Long receiverID, String content, LocalDateTime dateSent) {
        this. senderID =  senderID;
        this.receiverID = receiverID;
        this.content = content;
        this.dateSent = dateSent;
    }

    public Message(Long  senderID, Long receiverID, String content) {
        this. senderID =  senderID;
        this.receiverID = receiverID;
        this.content = content;
    }
    @Override
    public String toString() {
        return "Message{" +
                " senderID=" +  senderID +
                ", content='" + content + '\'' +
                ", dateSent=" + dateSent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals( senderID, message. senderID) && Objects.equals(content, message.content) && Objects.equals(dateSent, message.dateSent);
    }

    @Override
    public int hashCode() {
        return Objects.hash( senderID, content, dateSent);
    }

    public Long getSenderID() {
        return  senderID;
    }

    public void setSenderID(Long  senderID) {
        this. senderID =  senderID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDateTime dateSent) {
        this.dateSent = dateSent;
    }
}