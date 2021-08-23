package ru.zvo.datalayer.dto;

import java.util.Date;

public class Message {

    private long id;
    private Date dateStamp;
    private String messageContent;
    private User author;

    public Message() {
    }

    public Message(long id, Date dateStamp, String messageContent, User author) {
        this.id = id;
        this.dateStamp = dateStamp;
        this.messageContent = messageContent;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public Date getDateStamp() {
        return dateStamp;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public User getAuthor() {
        return author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDateStamp(Date dateStamp) {
        this.dateStamp = dateStamp;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", dateStamp=" + dateStamp +
                ", messageContent='" + messageContent + '\'' +
                ", author=" + author +
                '}';
    }
}
