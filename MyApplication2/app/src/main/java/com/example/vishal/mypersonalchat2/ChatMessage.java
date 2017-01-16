package com.example.vishal.mypersonalchat2;

/**
 * Created by Vishal on 12/21/2016.
 */
public class ChatMessage { String name;
    String mymessage;

    public ChatMessage() {
    }

    public ChatMessage(String name, String mymessage) {
        this.name = name;
        this.mymessage = mymessage;
    }

    public String getName() {
        return name;
    }

    public String getMymessage() {
        return mymessage;
    }
}

