package com.nadernabil.simpletwitterclient.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
    Context context;
    public FollowersAsyncTask(Context context , FollowersContract.FollowersPresenter followersPresenter, Long current_user_id, Long curser, Twitter twitter) {
        this.followersPresenter = followersPresenter;
        this.current_user_id = current_user_id;
        this.curser = curser;
        this.twitter = twitter;
        this.context = context;
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
        if (users == null){
            followersPresenter.ShowEmptyData();
            Toast.makeText(context, "Rate Limit", Toast.LENGTH_SHORT).show();
        }else {
            if(curser>0){
                followersPresenter.SetDataInViewReloaded(users,users.getNextCursor());
            }else {
                followersPresenter.SetDataInViewFirstTime(users,users.getNextCursor());
            }
        }

    }
}
