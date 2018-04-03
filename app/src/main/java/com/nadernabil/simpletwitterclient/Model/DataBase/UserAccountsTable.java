package com.nadernabil.simpletwitterclient.Model.DataBase;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class UserAccountsTable {

    public static final String TABLE_USER = "table_user";
    public static final String COL_USER_ID = " col_user_id";
    public static final String COL_USER_NAME = " col_user_name";
    public static final String COL_USER_HANDLE = "col_user_handle";
    public static final String COL_USER_PROFILE_IMAGE = " col_user_profile_image";
    public static final String COL_USER_BG_IMAGE = " col_user_bg_image";

    public static final String DB_USER = "CREATE TABLE " + TABLE_USER + "(" + COL_USER_ID + " INTEGER PRIMARY KEY, "
            + COL_USER_NAME + " TEXT, " + COL_USER_HANDLE + " TEXT, " + COL_USER_PROFILE_IMAGE + " TEXT, " + COL_USER_BG_IMAGE + " TEXT " + ")";


}
