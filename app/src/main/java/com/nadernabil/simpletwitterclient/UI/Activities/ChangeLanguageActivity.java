package com.nadernabil.simpletwitterclient.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nadernabil.simpletwitterclient.R;
import com.nadernabil.simpletwitterclient.Utils.AppController;
import com.nadernabil.simpletwitterclient.Utils.StorageUtil;

import java.util.Locale;

public class ChangeLanguageActivity extends AppCompatActivity {
    private static boolean active = false;
    private ImageView save;
    private TextView Textlang, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
    }

//    @Override
//    public void onBackPressed() {
//        String lang = AppController.getInstance().GetLang();
//        if (!lang.toLowerCase().contains(StorageUtil.getInstance().doStuff(this).getLang())) {
//            if (StorageUtil.getInstance().doStuff(this).getLang().equals("ar")) {
//                Locale arabic = new Locale("ar");
//                AppController.getInstance().SetLang(arabic, this);
//                StorageUtil.getInstance().doStuff(this).setLang("ar");
//            } else {
//                AppController.getInstance().SetLang(Locale.US, this);
//                StorageUtil.getInstance().doStuff(this).setLang("en");
//            }
//
//                startActivity(new Intent(this, UserMainActivity.class).putExtra("from", "2"));
//
//
//        } else {
//            if (StorageUtil.getInstance().getUTYPE().equals("4")) {
//                startActivity(new Intent(this, UserMainActivity.class).putExtra("from", "2"));
//            } else {
//                startActivity(new Intent(this, DriverMainActivity.class));
//            }
//        }
//    }


    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }
}
