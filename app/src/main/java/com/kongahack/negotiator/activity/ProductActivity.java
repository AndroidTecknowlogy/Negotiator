package com.kongahack.negotiator.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.kongahack.negotiator.GlobalVariables;
import com.kongahack.negotiator.R;
import com.kongahack.negotiator.adapter.ProductAdapter;
import com.kongahack.negotiator.model.ProductItem;

import java.util.ArrayList;

/**
 * Created by nezspencer on 10/29/16.
 */

public class ProductActivity extends AppCompatActivity {
    private RecyclerView productRecycler;
    private static String [] productNames;

    private static String [] sellerNames;

    private static String[] productPrice;

    private static ArrayList<Drawable> productImages;
    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_products);
        productPrice=getResources().getStringArray(R.array.prices);
        productNames=getResources().getStringArray(R.array.product_name);
        sellerNames=getResources().getStringArray(R.array.seller_name);
        productImages=new ArrayList<>();

        //add some dummy product images to the list
        productImages.add(getResources().getDrawable(R.drawable.m));
        productImages.add(getResources().getDrawable(R.drawable.m1));
        productImages.add(getResources().getDrawable(R.drawable.m2));
        productImages.add(getResources().getDrawable(R.drawable.m3));
        productImages.add(getResources().getDrawable(R.drawable.m4));
        productImages.add(getResources().getDrawable(R.drawable.m5));


        productRecycler=(RecyclerView)findViewById(R.id.product_recycler);


        // add dummy productItems to the global ProductItemList;
        GlobalVariables app=GlobalVariables.appInstance;
        for (int i=0; i<productImages.size(); i++)
        {
            ProductItem item=new ProductItem(
                    productNames[i],
                    sellerNames[i],
                    productPrice[i],
                    productImages.get(i)
            );
            app.addProductItem(item);
        }

        productAdapter=new ProductAdapter(this,app.getMyProducts());
        productRecycler.setAdapter(productAdapter);



    }
}
