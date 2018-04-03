package com.nadernabil.simpletwitterclient.Presenters;

import android.content.Context;

import com.nadernabil.simpletwitterclient.AsyncTasks.FollowersAsyncTask;
import com.nadernabil.simpletwitterclient.Bases.FollowersContract;
import com.nadernabil.simpletwitterclient.Model.DataBase.FollowersOperations;
import com.nadernabil.simpletwitterclient.Model.Objects.Follower;
import com.nadernabil.simpletwitterclient.Utils.GMethods;

import java.util.ArrayList;

import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class FollowersListPresenter implements FollowersContract.FollowersPresenter {

    private FollowersOperations followersOperations;
    private FollowersContract.FollowersView view;
    private Long currentUserUid;
    private Twitter twitter;

    public FollowersListPresenter(FollowersContract.FollowersView view, Long currentUserUid, Context context) {

        this.followersOperations = new FollowersOperations(context);
        this.view = view;
        this.currentUserUid = currentUserUid;

        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(GMethods.TWITTER_CONSUMER_KEY);
        builder.setOAuthConsumerSecret(GMethods.TWITTER_CONSUMER_SECRET);
        Configuration configuration = builder.build();

        TwitterFactory factory = new TwitterFactory(configuration);
        twitter = factory.getInstance();

    }

    @Override
    public void GetDataFromService(Long curser) {
        FollowersAsyncTask task = new FollowersAsyncTask(this, currentUserUid, curser, twitter);
        task.execute();
    }

    @Override
    public void GetDataFromLocalDB() {
        ArrayList<Follower> followers = followersOperations.getAllData(currentUserUid);
        if (followers.isEmpty()) {
            view.ShowEmptyData();
        } else {
            view.SetDataFirstTime(followers, Long.parseLong("-1"));
        }

    }

    @Override
    public void GetData(Long curser) {
        if (view.IsOnLine()) {
            GetDataFromService(curser);
        } else {
            GetDataFromLocalDB();
        }
    }

    @Override
    public void InsertFollowersToDb(PagableResponseList<User> users) {
        for (User user : users) {
            followersOperations.InsertFollower(user, currentUserUid);
        }
    }

    @Override
    public void SetDataInViewFirstTime(PagableResponseList<User> users, Long curser) {
        if (users.isEmpty()) {
            view.ShowEmptyData();
        } else {
            InsertFollowersToDb(users);
            view.SetDataFirstTime(ConvertUsersToFollowers(users), curser);
        }
    }

    @Override
    public void SetDataInViewReloaded(PagableResponseList<User> users, Long curser) {
        InsertFollowersToDb(users);
        view.SetReloadedData(ConvertUsersToFollowers(users), curser);
    }

    @Override
    public ArrayList<Follower> ConvertUsersToFollowers(PagableResponseList<User> users) {
        ArrayList<Follower> followers = new ArrayList<>();
        for (User user : users) {
            followers.add(new Follower(user.getId(),
                    user.getScreenName(),
                    user.getName(),
                    user.getDescription(),
                    user.getProfileImageURL(),
                    user.getProfileBannerMobileURL(),
                    this.currentUserUid
            ));
        }
        return followers;
    }
}
