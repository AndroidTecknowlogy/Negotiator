package com.kongahack.negotiator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.kongahack.negotiator.R;
import com.kongahack.negotiator.activity.ProductActivity;
import com.kongahack.negotiator.app.GlobalVariables;
import com.kongahack.negotiator.helper.Constants;
import com.kongahack.negotiator.model.ProductItem;
import com.kongahack.negotiator.model.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nezspencer on 10/29/16.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder> {

    public static ArrayList<ProductItem> productItems;
    private static Context context;
    private static int itemPosition;
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
        itemPosition=position;

        holder.productName.setText(item.getProductName());
        holder.sellerName.setText(item.getSellerName());
        holder.price.setText(item.getPrice());
        holder.productPix.setImageDrawable(item.getProductImage());


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

            /*chatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText( context, "clicked", Toast.LENGTH_SHORT).show();


                    DatabaseReference reference=GlobalVariables.appInstance.getDatabaseReference();
                    DatabaseReference usersRef=reference.child(Constants.USER_REF);

                    String sellerName=GlobalVariables.appInstance.getMyProducts().get(itemPosition)
                            .getSellerName();

                    String sellerChatId=GlobalVariables.appInstance.getMyProducts().get
                            (itemPosition)
                            .getSellerChatID();
                    User user=new User(sellerName,sellerChatId);
                    HashMap<String,Object> userMap=user.toMap();

                    usersRef.updateChildren(userMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError!=null)
                                Toast.makeText(context,databaseError.getDetails(),Toast
                                        .LENGTH_SHORT)
                                .show();
                                ProductActivity.activityInstance.openChatFragment(itemPosition);
                        }
                    });

                }
            });*/
        }
    }
}
