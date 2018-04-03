package com.nadernabil.simpletwitterclient.Model.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class MainContractHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "twitter_simple_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = MainContractHelper.class.getSimpleName();

    public MainContractHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserAccountsTable.DB_USER);
        db.execSQL(FollowersTable.DB_FOLLOWERS);
        db.execSQL(TweetsTable.DB_TWEETS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserAccountsTable.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + FollowersTable.TABLE_FOLLOWER);
        db.execSQL("DROP TABLE IF EXISTS " + TweetsTable.TABLE_TWEETS);
        onCreate(db);
    }
}
