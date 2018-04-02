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


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable() ;
    }
}
