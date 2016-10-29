package com.kongahack.negotiator.model;

import android.graphics.drawable.Drawable;

/**
 * Created by nezspencer on 10/29/16.
 */

public class ProductItem {

    private String productName;
    private String sellerName;
    private String price;
    private Drawable productImage;

    public ProductItem(String productName, String sellerName, String price, Drawable productImage) {
        this.productName = productName;
        this.sellerName = sellerName;
        this.price = price;
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Drawable getProductImage() {
        return productImage;
    }

    public void setProductImage(Drawable productImage) {
        this.productImage = productImage;
    }
}
