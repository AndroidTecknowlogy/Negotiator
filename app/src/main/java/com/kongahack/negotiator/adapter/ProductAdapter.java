package com.kongahack.negotiator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongahack.negotiator.R;
import com.kongahack.negotiator.model.ProductItem;

import java.util.ArrayList;

/**
 * Created by nezspencer on 10/29/16.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder> {

    private ArrayList<ProductItem> productItems;
    private Context context;
    public ProductAdapter(Context context,ArrayList<ProductItem> productItems) {
        this.productItems=productItems;
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        ProductItem item=productItems.get(position);

        holder.productName.setText(item.getProductName());
        holder.sellerName.setText(item.getSellerName());
        holder.price.setText(item.getPrice());
        holder.productPix.setImageBitmap(item.getProductImage());


    }

    @Override
    public int getItemCount() {
        return productItems.size();
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
