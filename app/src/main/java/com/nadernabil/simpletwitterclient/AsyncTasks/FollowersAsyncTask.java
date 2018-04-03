package com.nadernabil.simpletwitterclient.AsyncTasks;

import android.os.AsyncTask;

import com.nadernabil.simpletwitterclient.Bases.FollowersContract;

import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Created by NaderNabil@gmail.com on 4/3/2018.
 */

public class FollowersAsyncTask extends AsyncTask<Void, Void, PagableResponseList<User>> {
    FollowersContract.FollowersPresenter followersPresenter;
    Long current_user_id, curser;
    Twitter twitter;
    PagableResponseList<User> users;

    public FollowersAsyncTask(FollowersContract.FollowersPresenter followersPresenter, Long current_user_id, Long curser, Twitter twitter) {
        this.followersPresenter = followersPresenter;
        this.current_user_id = current_user_id;
        this.curser = curser;
        this.twitter = twitter;
    }

    @Override
    protected PagableResponseList<User> doInBackground(Void... voids) {
        try {
            users = twitter.getFollowersList(current_user_id, curser, 20);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    protected void onPostExecute(PagableResponseList<User> users) {
        super.onPostExecute(users);
        if(curser>0){
            followersPresenter.SetDataInViewReloaded(users,users.getNextCursor());
        }else {
            followersPresenter.SetDataInViewFirstTime(users,users.getNextCursor());
        }
    }
}
