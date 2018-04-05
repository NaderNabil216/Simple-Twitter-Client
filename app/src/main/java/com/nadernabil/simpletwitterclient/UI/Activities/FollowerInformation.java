package com.nadernabil.simpletwitterclient.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nadernabil.simpletwitterclient.Adapters.TweetsAdapter;
import com.nadernabil.simpletwitterclient.Bases.FollowerInformationContract;
import com.nadernabil.simpletwitterclient.Model.Objects.Follower;
import com.nadernabil.simpletwitterclient.Model.Objects.Tweet;
import com.nadernabil.simpletwitterclient.Presenters.FollowerInfoPresenter;
import com.nadernabil.simpletwitterclient.R;
import com.nadernabil.simpletwitterclient.Utils.GMethods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FollowerInformation extends AppCompatActivity implements FollowerInformationContract.InfoView {
    private boolean IsOnline;
    private TweetsAdapter tweetsAdapter;
    private RecyclerView recyclerView;
    private Follower follower;
    private TextView tv_name, tv_handle, tv_bio, txt_no_data;
    private ImageView header_image, profile_image;
    private Toolbar toolbar;
    private FollowerInfoPresenter presenter;
    private ProgressBar progress_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower_information);
        IsOnline = GMethods.isNetworkAvailable(this);
        Intent intent = getIntent();
        if (intent != null) {
            follower = GMethods.STRING_TO_FOLLOWER(intent.getStringExtra(GMethods.FOLLOWER));
        }
        InitViews();
        SetFollowerData();
        presenter = new FollowerInfoPresenter(this, follower.getId(), this);
        presenter.GetData();
    }

    @Override
    public void InitViews() {
        tv_name = findViewById(R.id.txt_user_screen_name);
        tv_handle = findViewById(R.id.txt_user_handle);
        tv_bio = findViewById(R.id.txt_user_bio);
        txt_no_data = findViewById(R.id.txt_no_data);
        progress_bar = findViewById(R.id.progress_bar);

        header_image = findViewById(R.id.header_image);
        profile_image = findViewById(R.id.profile_image);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(follower.getUser_name());
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void SetFollowerData() {
        Picasso.with(this).load(follower.getProfile_image()).placeholder(R.drawable.twitter_default).into(profile_image);
        Picasso.with(this).load(follower.getBackGround_Image()).placeholder(R.drawable.default_bg).into(header_image);
        tv_name.setText(follower.getUser_name());
        tv_handle.setText(follower.getHandle());
        tv_bio.setText(follower.getBio());
    }

    @Override
    public void SetData(ArrayList<Tweet> tweets) {
        progress_bar.setVisibility(View.GONE);
        tweetsAdapter = new TweetsAdapter(tweets, this, follower);
        recyclerView.setAdapter(tweetsAdapter);
    }

    @Override
    public void ShowEmptyData() {
        progress_bar.setVisibility(View.GONE);
        txt_no_data.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean IsOnLine() {
        return this.IsOnline;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
