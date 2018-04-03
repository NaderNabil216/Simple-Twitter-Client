package com.nadernabil.simpletwitterclient.Model.DataBase;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class TweetsTable {

    public static final String TABLE_TWEETS = "table_tweets";
    public static final String COL_TWEET_ID = " col_tweet_id";
    public static final String COL_TWEET_CONTENT = " col_tweet_content";
    public static final String COL_FOLLOWER_ID = " col_follower_id";

    public static final String DB_TWEETS = "CREATE TABLE " + TABLE_TWEETS + "(" + COL_TWEET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL_TWEET_CONTENT + " TEXT , " + COL_FOLLOWER_ID + " INTEGER " + ")";

}
