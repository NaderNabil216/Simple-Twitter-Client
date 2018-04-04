package com.nadernabil.simpletwitterclient.Model.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nadernabil.simpletwitterclient.Model.Objects.Follower;
import com.nadernabil.simpletwitterclient.Model.Objects.Tweet;
import com.nadernabil.simpletwitterclient.Utils.GMethods;

import java.util.ArrayList;

import twitter4j.Status;
import twitter4j.User;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class TweetsOperations {
    MainContractHelper contract;

    public TweetsOperations(Context context) {
        contract = new MainContractHelper(context);
    }

    public long InsertTweet (Status status, Long current_follower_id) {

        SQLiteDatabase db = contract.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TweetsTable.COL_TWEET_ID, status.getId());
        contentValues.put(TweetsTable.COL_TWEET_CONTENT, status.getText());
        contentValues.put(TweetsTable.COL_FOLLOWER_ID, current_follower_id);
        contentValues.put(TweetsTable.COL_TWEET_CREATED_AT, GMethods.DATE_TO_STRING(status.getCreatedAt()) );
        
        long done = db.insert(TweetsTable.TABLE_TWEETS, null, contentValues);
        db.close();
        return done;
    }

    public ArrayList<Tweet> getAllData(Long current_follower_id) {
        ArrayList<Tweet> data = new ArrayList<>();
        SQLiteDatabase db = contract.getWritableDatabase();
        String[] colomns = {TweetsTable.COL_TWEET_ID,
                TweetsTable.COL_TWEET_CONTENT,
                TweetsTable.COL_FOLLOWER_ID,
                TweetsTable.COL_TWEET_CREATED_AT};

        String Cluser = TweetsTable.COL_FOLLOWER_ID + " ='" + current_follower_id + "'";

        Cursor cursor = db.query(TweetsTable.TABLE_TWEETS, colomns, Cluser, null, null, null, null);
        while (cursor.moveToNext()) {
            Tweet tweet = new Tweet(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getLong(2),
                    cursor.getString(3));
            data.add(tweet);
        }
        return data;
    }

    public boolean IsEmpty(Long current_follower_id) {
        ArrayList<Tweet> tweets = getAllData(current_follower_id);
        return tweets.isEmpty();
    }


}
