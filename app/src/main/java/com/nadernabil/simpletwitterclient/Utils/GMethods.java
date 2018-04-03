package com.nadernabil.simpletwitterclient.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable() ;
    }
}
