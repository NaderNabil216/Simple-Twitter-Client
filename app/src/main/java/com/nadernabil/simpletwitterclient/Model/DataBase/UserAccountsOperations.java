package com.nadernabil.simpletwitterclient.Model.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nadernabil.simpletwitterclient.Model.Objects.UserAccount;

import java.util.ArrayList;

import twitter4j.User;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class UserAccountsOperations {

    MainContractHelper contract;

    public UserAccountsOperations(Context context) {
        contract = new MainContractHelper(context);
    }

    public long insert_user(User user) {
        SQLiteDatabase db = contract.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserAccountsTable.COL_USER_ID, user.getId());
        contentValues.put(UserAccountsTable.COL_USER_NAME, user.getName());
        contentValues.put(UserAccountsTable.COL_USER_HANDLE, "@" +user.getScreenName() );
        contentValues.put(UserAccountsTable.COL_USER_BIO, user.getDescription());
        contentValues.put(UserAccountsTable.COL_USER_PROFILE_IMAGE, user.getOriginalProfileImageURL());
        contentValues.put(UserAccountsTable.COL_USER_BG_IMAGE, user.getProfileBannerRetinaURL());
        long done = db.insert(UserAccountsTable.TABLE_USER, null, contentValues);
        db.close();
        return done;
    }

    public ArrayList<UserAccount> getAllData() {
        ArrayList<UserAccount> data = new ArrayList<>();
        SQLiteDatabase db = contract.getWritableDatabase();
        String[] colomns = {UserAccountsTable.COL_USER_ID,
                UserAccountsTable.COL_USER_NAME,
                UserAccountsTable.COL_USER_HANDLE,
                UserAccountsTable.COL_USER_BIO,
                UserAccountsTable.COL_USER_PROFILE_IMAGE,
                UserAccountsTable.COL_USER_BG_IMAGE};

        Cursor cursor = db.query(UserAccountsTable.TABLE_USER, colomns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            UserAccount userAccount = new UserAccount(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
            data.add(userAccount);
        }
        return data;
    }

    public UserAccount getUser(Long id) {
        ArrayList<UserAccount> data = new ArrayList<>();
        SQLiteDatabase db = contract.getWritableDatabase();
        String[] colomns = {UserAccountsTable.COL_USER_ID,
                UserAccountsTable.COL_USER_NAME,
                UserAccountsTable.COL_USER_HANDLE,
                UserAccountsTable.COL_USER_BIO,
                UserAccountsTable.COL_USER_PROFILE_IMAGE,
                UserAccountsTable.COL_USER_BG_IMAGE};

        String Cluser = UserAccountsTable.COL_USER_ID + " ='" + id + "'";
        Cursor cursor = db.query(UserAccountsTable.TABLE_USER, colomns, Cluser, null, null, null, null);
        while (cursor.moveToNext()) {
            UserAccount userAccount = new UserAccount(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
            data.add(userAccount);
        }
        return data.get(0);
    }

    public int DeleteAccounts(){
        SQLiteDatabase db = contract.getWritableDatabase();
        int count = db.delete(UserAccountsTable.TABLE_USER, null, null);
        return count;
    }

    public boolean IsEmpty() {
        ArrayList<UserAccount> userAccounts = getAllData();
        return userAccounts.isEmpty();
    }
}
