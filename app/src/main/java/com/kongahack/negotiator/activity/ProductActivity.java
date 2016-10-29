package com.kongahack.negotiator.activity;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kongahack.negotiator.R;
import com.kongahack.negotiator.app.GlobalVariables;
import com.kongahack.negotiator.fragment.BoarderFragment;
import com.kongahack.negotiator.fragment.ChatFragment;
import com.kongahack.negotiator.fragment.ProductListFragment;
import com.kongahack.negotiator.helper.Constants;
import com.kongahack.negotiator.model.ProductItem;

import java.util.ArrayList;

/**
 * Created by nezspencer on 10/29/16.
 */

public class ProductActivity extends AppCompatActivity {

    private static String [] productNames;

    private static String [] sellerNames;

    private static String[] productPrice;

    private static ArrayList<Drawable> productImages;
    public static ProductActivity activityInstance;

    private static String[] chatIDs;

    private static BoarderFragment boarderFragment;

    private final String CHAT = "chat_fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activityInstance=this;
        setContentView(R.layout.activity_product_view);


        boarderFragment = (BoarderFragment) getSupportFragmentManager()
                .findFragmentById(R.id.layout_boarder);

        productPrice=getResources().getStringArray(R.array.prices);
        productNames=getResources().getStringArray(R.array.product_name);
        sellerNames=getResources().getStringArray(R.array.seller_name);
        chatIDs=getResources().getStringArray(R.array.chat_id);
        productImages=new ArrayList<>();

        //add some dummy product images to the list
        productImages.add(getResources().getDrawable(R.drawable.polystar));
        productImages.add(getResources().getDrawable(R.drawable.samsung));
        productImages.add(getResources().getDrawable(R.drawable.samsunginch));
        productImages.add(getResources().getDrawable(R.drawable.lg));
        productImages.add(getResources().getDrawable(R.drawable.lgg));
        productImages.add(getResources().getDrawable(R.drawable.blue_gate));





        // add dummy productItems to the global ProductItemList;
        GlobalVariables app=GlobalVariables.appInstance;
        for (int i=0; i<productImages.size(); i++)
        {
            ProductItem item=new ProductItem(
                    productNames[i],
                    sellerNames[i],
                    productPrice[i],
                    productImages.get(i),
                    chatIDs[i]
            );
            app.addProductItem(item);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container_main,new ProductListFragment())
                .commit();

    }

    public void openChatFragment(int itemPosition)
    {
        ChatFragment fragment=new ChatFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(Constants.KEY_ITEM_POSITION,itemPosition);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main,fragment)
                .commit();
    }
}
