package com.nadernabil.simpletwitterclient.Presenters;

import android.content.Context;

import com.nadernabil.simpletwitterclient.Bases.FollowerInformationContract;
import com.nadernabil.simpletwitterclient.Bases.FollowersContract;
import com.nadernabil.simpletwitterclient.Model.DataBase.FollowersOperations;
import com.nadernabil.simpletwitterclient.Model.DataBase.TweetsOperations;
import com.nadernabil.simpletwitterclient.Model.Objects.Tweet;
import com.nadernabil.simpletwitterclient.Utils.GMethods;

import java.util.ArrayList;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class FollowerInfoPresenter implements FollowerInformationContract.InfoPresenter {
    private TweetsOperations tweetsOperations;
    private FollowerInformationContract.InfoView view;
    private Long currentFollowerUid;
    private Twitter twitter;
    private Context context;

    public FollowerInfoPresenter(TweetsOperations tweetsOperations, FollowerInformationContract.InfoView view, Long currentFollowerUid, Context context) {
        this.tweetsOperations = tweetsOperations;
        this.view = view;
        this.currentFollowerUid = currentFollowerUid;
        this.context = context;

        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(GMethods.TWITTER_CONSUMER_KEY);
        builder.setOAuthConsumerSecret(GMethods.TWITTER_CONSUMER_SECRET);
        builder.setOAuthAccessToken(GMethods.TWITTER_ACCESS_TOKEN);
        builder.setOAuthAccessTokenSecret(GMethods.TWITTER_ACCESS_TOKEN_SECRET);
        Configuration configuration = builder.build();

        TwitterFactory factory = new TwitterFactory(configuration);
        twitter = factory.getInstance();

    }

    @Override
    public void GetDataFromService() {

    }

    @Override
    public void GetDataFromLocalDB() {

    }

    @Override
    public void GetData() {

    }

    @Override
    public void InsertTweetsToDb(ResponseList<Status> statuses) {

    }

    @Override
    public void SetDataInView(ResponseList<Status> statuses) {

    }

    @Override
    public ArrayList<Tweet> ConvertStatusToTweet(ResponseList<Status> statuses) {
        return null;
    }

    @Override
    public void ShowEmptyData() {

    }
}
