package com.kongahack.negotiator.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.kongahack.negotiator.R;
import com.kongahack.negotiator.model.ChatItem;

import java.util.ArrayList;

/**
 * Created by nezspencer on 10/29/16.
 */

public class ChatAdapter extends ArrayAdapter<ChatItem> {

    public ChatAdapter(Context context, ArrayList<ChatItem> chats) {
        super(context, R.layout.layout_chat_item,chats);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
