package com.nadernabil.simpletwitterclient.UI.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nadernabil.simpletwitterclient.Bases.FollowersContract;
import com.nadernabil.simpletwitterclient.Model.Objects.Follower;

import java.util.ArrayList;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class FollowersList extends Fragment implements FollowersContract.FollowersView {
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void InitViews(View view) {

    }

    @Override
    public void SetPresenter(FollowersContract.FollowersPresenter presenter) {

    }

    @Override
    public void SetDataFirstTime(ArrayList<Follower> followers, Long curser) {

    }

    @Override
    public void SetReloadedData(ArrayList<Follower> followers, Long curser) {

    }

    @Override
    public void ShowEmptyData() {

    }

    @Override
    public boolean IsOnLine() {
        return false;
    }
}
