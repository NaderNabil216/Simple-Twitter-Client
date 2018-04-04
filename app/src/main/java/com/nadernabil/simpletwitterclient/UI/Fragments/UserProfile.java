package com.nadernabil.simpletwitterclient.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nadernabil.simpletwitterclient.Adapters.UserAccountsAdapter;
import com.nadernabil.simpletwitterclient.Bases.ProfileContract;
import com.nadernabil.simpletwitterclient.Model.Objects.UserAccount;
import com.nadernabil.simpletwitterclient.Presenters.UserProfilePresenter;
import com.nadernabil.simpletwitterclient.R;
import com.nadernabil.simpletwitterclient.UI.Activities.ChangeLanguageActivity;
import com.nadernabil.simpletwitterclient.UI.Activities.LoginActivity;
import com.nadernabil.simpletwitterclient.Utils.GMethods;
import com.nadernabil.simpletwitterclient.Utils.StorageUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class UserProfile extends Fragment implements ProfileContract.profile_view, View.OnClickListener {
    boolean IsActive = false;
    private UserAccountsAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tv_name, tv_handle, tv_bio, tv_addAccount, tv_change_language, tv_logout;
    private ImageView header_image, profile_image;
    private Toolbar toolbar;
    private UserProfilePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_frag_layout, container, false);
        InitViews(view);
        presenter = new UserProfilePresenter(getActivity(), StorageUtil.getInstance().doStuff(getActivity()).GetUID(), this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (IsActive) {
            presenter.GetData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IsActive = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        IsActive = false;
    }


    @Override
    public void InitViews(View view) {
        tv_name = view.findViewById(R.id.txt_user_screen_name);
        tv_handle = view.findViewById(R.id.txt_user_handle);
        tv_bio = view.findViewById(R.id.txt_user_bio);
        tv_addAccount = view.findViewById(R.id.AddAccount);
        tv_change_language = view.findViewById(R.id.ChangeLanguage);
        tv_logout = view.findViewById(R.id.LogOut);

        tv_addAccount.setOnClickListener(this);
        tv_change_language.setOnClickListener(this);
        tv_logout.setOnClickListener(this);

        header_image = view.findViewById(R.id.header_image);
        profile_image = view.findViewById(R.id.profile_image);
        toolbar = view.findViewById(R.id.toolbar);

        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void SetDataUsers(ArrayList<UserAccount> userAccounts) {
        adapter = new UserAccountsAdapter(userAccounts, this, getActivity(), StorageUtil.getInstance().doStuff(getActivity()).GetUID());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void SetDataCurrentUser(UserAccount account) {
        Picasso.with(getActivity()).load(account.getProfile_image()).placeholder(R.drawable.twitter_default).into(profile_image);
        Picasso.with(getActivity()).load(account.getBackGround_Image()).placeholder(R.drawable.default_bg).into(header_image);
        tv_name.setText(account.getUser_name());
        tv_handle.setText(account.getUser_handle());
        tv_bio.setText(account.getUser_bio());
        // toolbar.setTitle(account.getUser_name());
    }

    @Override
    public void AddNewAccount() {
        StorageUtil.getInstance().doStuff(getActivity()).setFromMainActivity(true);
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra(GMethods.From_Main_Activity, true);
        startActivity(intent);
    }

    @Override
    public void Switch_Account(Long choosen_account_id) {
        presenter.SwitchAccounts(choosen_account_id);
    }

    @Override
    public void LogOut() {
        StorageUtil.getInstance().doStuff(getActivity()).SetIsLogged(false);
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public void ChangeLanguage() {
        startActivity(new Intent(getActivity(), ChangeLanguageActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.AddAccount:
                AddNewAccount();
                break;
            case R.id.ChangeLanguage:
                ChangeLanguage();
                break;
            case R.id.LogOut:
                LogOut();
                break;

        }
    }
}
