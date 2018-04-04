package com.nadernabil.simpletwitterclient.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nadernabil.simpletwitterclient.R;
import com.nadernabil.simpletwitterclient.Utils.AppController;
import com.nadernabil.simpletwitterclient.Utils.StorageUtil;

import java.util.Locale;

public class ChangeLanguageActivity extends AppCompatActivity {
    private ImageView save;
    private TextView Textlang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        Toolbar toolbar = findViewById(R.id.lang_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        save = toolbar.findViewById(R.id.savelang);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeData();
            }
        });
        Textlang = findViewById(R.id.langText);
        Locale currentLocale = getResources().getConfiguration().locale;
        Textlang.setText(currentLocale.getDisplayName(currentLocale));
        Textlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppController.getInstance().StartLangDialog(ChangeLanguageActivity.this);
                Locale currentLocale = getResources().getConfiguration().locale;
                Textlang.setText(currentLocale.getDisplayName(currentLocale));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    private void ChangeData() {
        Locale currentLocale = getResources().getConfiguration().locale;
        final String lang = currentLocale.getDisplayLanguage();
        if (lang.equals("English")) {
            AppController.getInstance().SetLang(Locale.US, this);
            StorageUtil.getInstance().doStuff(this).setLang("en");
        } else {
            Locale arabic = new Locale("ar");
            AppController.getInstance().SetLang(arabic, this);
            StorageUtil.getInstance().doStuff(this).setLang("ar");
        }
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        String lang = AppController.getInstance().GetLang();
        if (lang == null) {
            Locale currentLocale = getResources().getConfiguration().locale;
            lang = currentLocale.getDisplayLanguage();
            if (lang.equals("English")) {
                Locale arabic = new Locale("ar");
                AppController.getInstance().SetLang(arabic, this);
                StorageUtil.getInstance().doStuff(this).setLang("ar");
            } else {
                AppController.getInstance().SetLang(Locale.US, this);
                StorageUtil.getInstance().doStuff(this).setLang("en");
            }
        } else {
            if (StorageUtil.getInstance().doStuff(this).getLang().equals("ar")) {
                Locale arabic = new Locale("ar");
                AppController.getInstance().SetLang(arabic, this);
                StorageUtil.getInstance().doStuff(this).setLang("ar");
            } else {
                AppController.getInstance().SetLang(Locale.US, this);
                StorageUtil.getInstance().doStuff(this).setLang("en");
            }
        }

        startActivity(new Intent(this, MainActivity.class));
    }


}
