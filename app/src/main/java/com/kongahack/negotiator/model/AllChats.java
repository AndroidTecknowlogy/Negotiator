package com.kongahack.negotiator.model;

import java.util.List;

/**
 * Created by nezspencer on 10/29/16.
 */

public class AllChats {

    private List<ChatItem> chatItemList;

    public AllChats() {
    }

    public List<ChatItem> getChatItemList() {
        return chatItemList;
    }

    public void setChatItemList(List<ChatItem> chatItemList) {
        this.chatItemList = chatItemList;
    }
}
