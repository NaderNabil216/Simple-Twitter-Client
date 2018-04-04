package com.nadernabil.simpletwitterclient.Presenters;

import android.content.Context;

import com.nadernabil.simpletwitterclient.AsyncTasks.TweetsAsyncTask;
import com.nadernabil.simpletwitterclient.Bases.FollowerInformationContract;
import com.nadernabil.simpletwitterclient.Model.DataBase.TweetsOperations;
import com.nadernabil.simpletwitterclient.Model.Objects.Follower;
import com.nadernabil.simpletwitterclient.Model.Objects.Tweet;
import com.nadernabil.simpletwitterclient.Utils.GMethods;

import java.util.ArrayList;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
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

    public FollowerInfoPresenter(FollowerInformationContract.InfoView view, Long currentFollowerUid, Context context) {
        this.tweetsOperations = new TweetsOperations(context);
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
        TweetsAsyncTask task = new TweetsAsyncTask(this, currentFollowerUid, twitter, context);
        task.execute();
    }

    @Override
    public void GetDataFromLocalDB() {
        ArrayList<Tweet> tweets = tweetsOperations.getAllData(currentFollowerUid);
        if (tweets.isEmpty()) {
            view.ShowEmptyData();
        } else {
            view.SetData(tweets);
        }
    }

    @Override
    public void GetData() {
        if (view.IsOnLine()) {
            GetDataFromService();
        }else {
            GetDataFromLocalDB();
        }
    }

    @Override
    public void InsertTweetsToDb(ResponseList<Status> statuses) {
        for (Status status : statuses){
            tweetsOperations.InsertTweet(status,currentFollowerUid);
        }
    }

    @Override
    public void SetDataInView(ResponseList<Status> statuses) {
        InsertTweetsToDb(statuses);
        if(statuses.isEmpty()){
            view.ShowEmptyData();
        }else {
            view.SetData(ConvertStatusToTweet(statuses));
        }

    }

    @Override
    public ArrayList<Tweet> ConvertStatusToTweet(ResponseList<Status> statuses) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (Status status : statuses) {
            tweets.add(new Tweet(status.getId(),status.getText(),currentFollowerUid,GMethods.DATE_TO_STRING(status.getCreatedAt())));
        }
        return tweets;
    }

    @Override
    public void ShowEmptyData() {
        view.ShowEmptyData();
    }
}
