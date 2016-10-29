package com.kongahack.negotiator.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by AGBOMA franklyn on 10/29/16.
 */
public class SmackContact {

    //Authority
    public static final String AUTHORITY = "com.kongahack.negotiator.provider";

    //Contract
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);

    //Paths
    public static final String PATH_SMACK = "smack";

    //interface for Tables in columns
    public interface SmackColumn {

        String SELLER_CHAT = "seller_chat";
        String BUYER_CHAT = "buyer_chat";

    }



    //classes Tables
    public static class Smack implements SmackColumn, BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_SMACK)
                .build();//content Uri

        //content dir
        public static final String CONTENT_DIR = ContentResolver.CURSOR_DIR_BASE_TYPE
                +"/vnd.net.id.franklyn.splasherstech.provider."+PATH_SMACK;

        //content item
        public static final String CONTENT_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE
                +"/vnd.com.kongahack.negotiator.provider."+PATH_SMACK;

        public static final String[] PROJECTION = {
                _ID, SELLER_CHAT, BUYER_CHAT
        };

        public static final String SORT_ORDER = _ID + " ASC";
    }
}
