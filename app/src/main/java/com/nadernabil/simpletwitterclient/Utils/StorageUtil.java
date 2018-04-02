package com.nadernabil.simpletwitterclient.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class StorageUtil {

    private static final StorageUtil ourInstance = new StorageUtil();

    public static StorageUtil getInstance() {
        return ourInstance;
    }

    private final String STORAGE = "com.nadernabil.simpletwitterclient.Utils.STORAGE";
    private SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private Context context;

    private StorageUtil() {
    }

    public StorageUtil doStuff(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        return ourInstance;
    }

    public void SetIsLogged(boolean state) {
        editor.putBoolean(GMethods.isLogged, state);
        editor.apply();
    }

    public boolean IsLogged() {
        return preferences.getBoolean(GMethods.isLogged, false);
    }

    public void SetUID(int uid) {
        editor.putInt(GMethods.UID, uid);
        editor.apply();
    }

    public int GetUID() {
        return preferences.getInt(GMethods.UID, 0);
    }

}
