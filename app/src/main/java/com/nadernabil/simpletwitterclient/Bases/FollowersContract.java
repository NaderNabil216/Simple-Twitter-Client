package com.nadernabil.simpletwitterclient.Bases;

import android.view.View;

import com.nadernabil.simpletwitterclient.Model.Objects.Follower;

import java.util.ArrayList;

import twitter4j.PagableResponseList;
import twitter4j.User;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public interface FollowersContract {

    interface FollowersView {

        void InitViews(View view);

        void SetDataFirstTime(ArrayList<Follower> followers, Long curser);

        void SetReloadedData(ArrayList<Follower> followers, Long curser);

        void ShowEmptyData();

        boolean IsOnLine();

    }

    interface FollowersPresenter {

        void GetDataFromService(final Long curser);

        void GetDataFromLocalDB();

        void GetData(final Long curser);

        void InsertFollowersToDb(PagableResponseList<User> users);

        void SetDataInViewFirstTime(PagableResponseList<User> users, Long curser);

        void SetDataInViewReloaded(PagableResponseList<User> users, Long curser);

        ArrayList<Follower> ConvertUsersToFollowers(PagableResponseList<User> users);

        void ShowEmptyData();

    }
}
