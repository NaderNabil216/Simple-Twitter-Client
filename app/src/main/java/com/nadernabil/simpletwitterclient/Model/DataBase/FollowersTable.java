package com.nadernabil.simpletwitterclient.Model.DataBase;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class FollowersTable {

    public static final String TABLE_FOLLOWER = "table_follower";
    public static final String COL_FOLLOWER_ID = " col_follower_id";
    public static final String COL_FOLLOWER_NAME = " col_follower_name";
    public static final String COL_FOLLOWER_PROFILE_IMAGE = " col_follower_profile_image";
    public static final String COL_FOLLOWER_BG_IMAGE = " col_follower_bg_image";
    public static final String COL_FOLLOWER_BIO = " col_follower_bio";
    public static final String COL_FOLLOWER_FOLLOWS = " col_follower_follows";

    public static final String DB_FOLLOWERS  = "CREATE TABLE " + TABLE_FOLLOWER + "(" + COL_FOLLOWER_ID + " INTEGER PRIMARY KEY  , "
            + COL_FOLLOWER_NAME + " TEXT , " + COL_FOLLOWER_PROFILE_IMAGE + " TEXT , " + COL_FOLLOWER_BG_IMAGE + " TEXT , " + COL_FOLLOWER_BIO + " TEXT , " + COL_FOLLOWER_FOLLOWS + " INTEGER "+ ")";


}
