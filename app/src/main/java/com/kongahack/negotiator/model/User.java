package com.kongahack.negotiator.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;

/**
 * Created by nezspencer on 10/29/16.
 */

public class User {

    private String sellerName;
    private String sellerChatId;

    public User() {
    }

    public User(String sellerName, String sellerChatId) {
        this.sellerName = sellerName;
        this.sellerChatId = sellerChatId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerChatId() {
        return sellerChatId;
    }

    public void setSellerChatId(String sellerChatId) {
        this.sellerChatId = sellerChatId;
    }

    @Exclude
    public HashMap<String,Object> toMap()
    {
        HashMap<String,Object> map=new HashMap<>();
        map.put("sellerName",sellerName);
        map.put("sellerChatId",sellerChatId);
        return map;
    }
}
