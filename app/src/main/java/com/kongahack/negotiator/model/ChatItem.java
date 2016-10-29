package com.kongahack.negotiator.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;

/**
 * Created by nezspencer on 10/29/16.
 */

public class ChatItem {

    private String message;
    private String isUser; //could be either yes or no; yes if the user is the one that sent the msg
                            //no if the user received the msg from a chat participant.

    /*required empty contrcutor for firebase*/
    public ChatItem() {
    }

    public ChatItem(String message, String isUser) {
        this.message = message;
        this.isUser = isUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsUser() {
        return isUser;
    }

    public void setIsUser(String isUser) {
        this.isUser = isUser;
    }

    @Exclude
    public HashMap<String,Object> toMap()
    {
        HashMap<String,Object> map=new HashMap<>();
        map.put("message",message);
        map.put("isUser",isUser);
        return map;
    }
}
