package com.nadernabil.simpletwitterclient.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nadernabil.simpletwitterclient.Model.Objects.Follower;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class GMethods {

    /**
     * Shared Preference Tags
     */
    public static String isLogged = "isLogged";
    public static String UID = "UID";

    public static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
    public static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";

    public static final String URL_TWITTER_AUTH = "auth_url";
    public static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
    public static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";
    public static final String TWITTER_CALLBACK_URL = "oauth://t4jsample";

    public static final String TWITTER_CONSUMER_KEY = "iLYrNpGOxkf9alcGXmiklmTnD";
    public static final String TWITTER_CONSUMER_SECRET = "N81RUHGA2vsIVcdKDXqYAw9okgfc5VOvE3b0uV5mUn80AhiPdY";
    public static final String TWITTER_ACCESS_TOKEN = "246861207-kHquVugaUgdmjrB5T5SkulvlIaFd7bDay52xMRWX";
    public static final String TWITTER_ACCESS_TOKEN_SECRET = "fUHoU27sFSeb6AlfP49FQ314f3MtTnKy4H8Sm5MpOnFPc";

    public static final String FOLLOWER="FOLLOWER";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable();
    }

    public static String DATE_TO_STRING(Date date) {
        DateFormat df = new SimpleDateFormat(" d MMM yyyy , hh:mm aaa ");
        return df.format(date);
    }

    public static String FOLLOWER_TO_STRING(Follower follower) {
        Gson gson = new Gson();
        return gson.toJson(follower);
    }

    public static Follower STRING_TO_FOLLOWER(String follower_str) {
        Gson gson = new Gson();
        Type type = new TypeToken<Follower>() {
        }.getType();
        return gson.fromJson(follower_str, type);
    }
}
