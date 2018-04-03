package com.nadernabil.simpletwitterclient.Model.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nadernabil.simpletwitterclient.Model.Objects.Follower;
import com.nadernabil.simpletwitterclient.Model.Objects.UserAccount;

import java.util.ArrayList;

import twitter4j.User;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class FollowersOperations {

    MainContractHelper contract;

    public FollowersOperations(Context context) {
        contract = new MainContractHelper(context);
    }

    public long InsertFollower(User user, Long current_user_id) {
        SQLiteDatabase db = contract.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FollowersTable.COL_FOLLOWER_ID, user.getId());
        contentValues.put(FollowersTable.COL_FOLLOWER_NAME, user.getScreenName());
        contentValues.put(FollowersTable.COL_FOLLOWER_HANDLE, user.getName());
        contentValues.put(FollowersTable.COL_FOLLOWER_PROFILE_IMAGE, user.getProfileImageURL());
        contentValues.put(FollowersTable.COL_FOLLOWER_BG_IMAGE, user.getProfileBannerMobileURL());
        contentValues.put(FollowersTable.COL_FOLLOWER_BIO, user.getDescription());
        contentValues.put(FollowersTable.COL_FOLLOWER_FOLLOWS, current_user_id);
        long done = db.insert(FollowersTable.TABLE_FOLLOWER, null, contentValues);
        db.close();
        return done;
    }

    public ArrayList<Follower> getAllData(Long current_user_id) {
        ArrayList<Follower> data = new ArrayList<>();
        SQLiteDatabase db = contract.getWritableDatabase();
        String[] colomns = {FollowersTable.COL_FOLLOWER_ID,
                FollowersTable.COL_FOLLOWER_NAME,
                FollowersTable.COL_FOLLOWER_HANDLE,
                FollowersTable.COL_FOLLOWER_PROFILE_IMAGE,
                FollowersTable.COL_FOLLOWER_BG_IMAGE,
                FollowersTable.COL_FOLLOWER_BIO,
                FollowersTable.COL_FOLLOWER_FOLLOWS};

        String Cluser = FollowersTable.COL_FOLLOWER_FOLLOWS + " ='" + current_user_id + "'";

        Cursor cursor = db.query(FollowersTable.TABLE_FOLLOWER, colomns, Cluser, null, null, null, null);
        int index1 = cursor.getColumnIndex(FollowersTable.COL_FOLLOWER_ID);
        int index2 = cursor.getColumnIndex(FollowersTable.COL_FOLLOWER_NAME);
        int index3 = cursor.getColumnIndex(FollowersTable.COL_FOLLOWER_HANDLE);
        int index4 = cursor.getColumnIndex(FollowersTable.COL_FOLLOWER_BIO);
        int index5 = cursor.getColumnIndex(FollowersTable.COL_FOLLOWER_PROFILE_IMAGE);
        int index6 = cursor.getColumnIndex(FollowersTable.COL_FOLLOWER_BG_IMAGE);
        int index7 = cursor.getColumnIndex(FollowersTable.COL_FOLLOWER_FOLLOWS);

        while (cursor.moveToNext()) {
            Follower follower = new Follower(cursor.getLong(index1),
                    cursor.getString(index2),
                    cursor.getString(index3),
                    cursor.getString(index4),
                    cursor.getString(index5),
                    cursor.getString(index6),
                    cursor.getLong(index7));
            data.add(follower);
        }
        return data;
    }

    public boolean IsEmpty(Long current_user_id){
        ArrayList<Follower> followers = getAllData(current_user_id);
        return followers.isEmpty();
    }


}
