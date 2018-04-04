package com.nadernabil.simpletwitterclient.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.nadernabil.simpletwitterclient.Bases.FollowerInformationContract;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by NaderNabil@gmail.com on 4/4/2018.
 */

public class TweetsAsyncTask extends AsyncTask<Void, Void, ResponseList<Status>> {
    FollowerInformationContract.InfoPresenter presenter;
    Long current_follower_id;
    Twitter twitter;
    ResponseList<twitter4j.Status> list;
    Context context;

    public TweetsAsyncTask(FollowerInformationContract.InfoPresenter presenter, Long current_follower_id, Twitter twitter, Context context) {
        this.presenter = presenter;
        this.current_follower_id = current_follower_id;
        this.twitter = twitter;
        this.context = context;
    }


    @Override
    protected ResponseList<twitter4j.Status> doInBackground(Void... voids) {
        try {
            list = twitter.getUserTimeline(current_follower_id);
        } catch (TwitterException e) {
            e.printStackTrace();

        }
        return list;
    }

    @Override
    protected void onPostExecute(ResponseList<twitter4j.Status> statuses) {
        super.onPostExecute(statuses);
        if (statuses == null) {
            presenter.ShowEmptyData();
        } else {
            presenter.SetDataInView(statuses);
        }
    }
}
