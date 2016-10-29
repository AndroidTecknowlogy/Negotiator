package com.kongahack.negotiator.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongahack.negotiator.R;

/**
 * Created by nezspencer on 10/29/16.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder> {

    public ProductAdapter() {
        super();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class Holder extends RecyclerView.ViewHolder{
        public TextView productName;
        public TextView sellerName;
        public ImageView productPix;
        public TextView price;
        public ImageButton shoppingCart;
        public ImageButton chatButton;

        public Holder(View itemView) {
            super(itemView);
            productName=(TextView)itemView.findViewById(R.id.product_name);
            productPix=(ImageView)itemView.findViewById(R.id.product_image);
            sellerName=(TextView)itemView.findViewById(R.id.seller_name);
            price=(TextView)itemView.findViewById(R.id.price);
            shoppingCart=(ImageButton)itemView.findViewById(R.id.shopping_cart);
            chatButton=(ImageButton)itemView.findViewById(R.id.chat_button);
        }
    }
}
