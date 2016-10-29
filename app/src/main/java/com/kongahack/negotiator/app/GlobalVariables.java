package com.kongahack.negotiator.app;

import android.app.Application;

import com.kongahack.negotiator.model.ProductItem;

import java.util.ArrayList;

/**
 * Created by nezspencer on 10/29/16.
 */

public class GlobalVariables extends Application {

    private ArrayList<ProductItem> myProducts; // a global arrayList containing all
    // productItems objects. visible from anywhere within the application

    public static GlobalVariables appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance=this;
        myProducts=new ArrayList<>();
    }

    public void addProductItem(ProductItem productItem){
        myProducts.add(productItem);
    }

    public ArrayList<ProductItem> getMyProducts() {
        return myProducts;
    }
}
