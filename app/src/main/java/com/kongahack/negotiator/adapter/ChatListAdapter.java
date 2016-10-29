package com.kongahack.negotiator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kongahack.negotiator.R;
import com.kongahack.negotiator.model.ProductItem;

import java.util.ArrayList;

/**
 * Created by nezspencer on 10/29/16.
 */

public class ChatListAdapter extends RecyclerView.Adapter {

    private static Context context;
    private static ArrayList<ProductItem> items;

    public ChatListAdapter(Context context,ArrayList<ProductItem> items) {

        this.context=context;
        this.items=items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class Holder extends RecyclerView.ViewHolder{
        public TextView sellerName;
        public Holder(View itemView) {
            super(itemView);
            sellerName=(TextView)itemView.findViewById(R.id.seller_item);
        }
    }
}
