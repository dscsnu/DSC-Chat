package com.studioseven.dsc_chat.Models;

public class Chat {
    String messageString;
    String time;

    public Chat(String messageString, String time) {
        this.messageString = messageString;
        this.time = time;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
