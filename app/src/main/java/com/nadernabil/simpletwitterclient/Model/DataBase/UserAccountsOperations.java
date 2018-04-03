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
        contract=new MainContractHelper(context);
    }

    public long insert_user(User user) {
        SQLiteDatabase db = contract.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserAccountsTable.COL_USER_ID, user.getId());
        contentValues.put(UserAccountsTable.COL_USER_NAME, user.getScreenName());
        contentValues.put(UserAccountsTable.COL_USER_HANDLE, user.getName());
        contentValues.put(UserAccountsTable.COL_USER_PROFILE_IMAGE, user.getProfileImageURL());
        contentValues.put(UserAccountsTable.COL_USER_BG_IMAGE, user.getProfileBannerMobileURL());
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
                UserAccountsTable.COL_USER_PROFILE_IMAGE,
                UserAccountsTable.COL_USER_BG_IMAGE};

        Cursor cursor = db.query(UserAccountsTable.TABLE_USER, colomns, null, null, null, null, null);
        int index1 = cursor.getColumnIndex(UserAccountsTable.COL_USER_ID);
        int index2 = cursor.getColumnIndex(UserAccountsTable.COL_USER_NAME);
        int index3 = cursor.getColumnIndex(UserAccountsTable.COL_USER_HANDLE);
        int index4 = cursor.getColumnIndex(UserAccountsTable.COL_USER_PROFILE_IMAGE);
        int index5 = cursor.getColumnIndex(UserAccountsTable.COL_USER_BG_IMAGE);
        while (cursor.moveToNext()) {
            UserAccount userAccount = new UserAccount(cursor.getLong(index1),
                    cursor.getString(index2),
                    cursor.getString(index3),
                    cursor.getString(index4),
                   cursor.getString(index5));
            data.add(userAccount);
        }
        return data;
    }

    public UserAccount getNote(String id) {
        ArrayList<UserAccount> data = new ArrayList<>();
        SQLiteDatabase db = contract.getWritableDatabase();
        String[] colomns = {UserAccountsTable.COL_USER_ID,
                UserAccountsTable.COL_USER_NAME,
                UserAccountsTable.COL_USER_HANDLE,
                UserAccountsTable.COL_USER_PROFILE_IMAGE,
                UserAccountsTable.COL_USER_BG_IMAGE};


        String Cluser = UserAccountsTable.COL_USER_ID + " ='" + id + "'";
        Cursor cursor = db.query(UserAccountsTable.TABLE_USER, colomns, Cluser, null, null, null, null);
        int index1 = cursor.getColumnIndex(UserAccountsTable.COL_USER_ID);
        int index2 = cursor.getColumnIndex(UserAccountsTable.COL_USER_NAME);
        int index3 = cursor.getColumnIndex(UserAccountsTable.COL_USER_HANDLE);
        int index4 = cursor.getColumnIndex(UserAccountsTable.COL_USER_PROFILE_IMAGE);
        int index5 = cursor.getColumnIndex(UserAccountsTable.COL_USER_BG_IMAGE);
        while (cursor.moveToNext()) {
            UserAccount userAccount = new UserAccount(cursor.getLong(index1),
                    cursor.getString(index2),
                    cursor.getString(index3),
                    cursor.getString(index4),
                    cursor.getString(index5));
            data.add(userAccount);
        }
        return data.get(0);
    }

    public boolean IsEmpty(){
        ArrayList<UserAccount> userAccounts = getAllData();
        return userAccounts.isEmpty();
    }
}
