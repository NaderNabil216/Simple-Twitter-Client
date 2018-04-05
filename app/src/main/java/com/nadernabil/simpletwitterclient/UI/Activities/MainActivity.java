package com.nadernabil.simpletwitterclient.UI.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nadernabil.simpletwitterclient.R;
import com.nadernabil.simpletwitterclient.UI.Fragments.FollowersList;
import com.nadernabil.simpletwitterclient.UI.Fragments.UserProfile;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitViews();
        Switch_Followers_Tab();
    }

    private void InitViews() {
        fm = getSupportFragmentManager();
        fm.findFragmentById(R.id.frame);
        getSupportActionBar().setTitle(getString(R.string.twitter));
    }

    private void Switch_Followers_Tab() {
        FollowersList followersList = new FollowersList();
        ft = fm.beginTransaction();
        ft.replace(R.id.frame, followersList);
        ft.commit();
    }

    private void Switch_Profile_Tab() {
        UserProfile profile = new UserProfile();
        ft = fm.beginTransaction();
        ft.replace(R.id.frame, profile);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_followers:
                Switch_Followers_Tab();
                return true;

            case R.id.navigation_profile:
                Switch_Profile_Tab();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
