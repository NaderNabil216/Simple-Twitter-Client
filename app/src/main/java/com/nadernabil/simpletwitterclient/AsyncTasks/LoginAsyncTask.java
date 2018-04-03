package com.nadernabil.simpletwitterclient.AsyncTasks;

import android.os.AsyncTask;

import com.nadernabil.simpletwitterclient.Bases.LoginContract;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import com.nadernabil.simpletwitterclient.Bases.LoginContract.LoginView;

/**
 * Created by NaderNabil@gmail.com on 4/3/2018.
 */

public class LoginAsyncTask extends AsyncTask<Void, Void, User> {

    private LoginView loginView;
    private RequestToken requestToken;
    private Twitter twitter;
    private String oauth_verifierParameter;
    private AccessToken aToken ;
    private User user ;

    public LoginAsyncTask(LoginView loginView, RequestToken requestToken, Twitter twitter, String oauth_verifierParameter) {
        this.loginView = loginView;
        this.requestToken = requestToken;
        this.twitter = twitter;
        this.oauth_verifierParameter = oauth_verifierParameter;
    }

    @Override
    protected User doInBackground(Void... params) {
        try {
           aToken = twitter.getOAuthAccessToken(requestToken, oauth_verifierParameter);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        try {
            twitter.verifyCredentials();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        try {
            long userID = aToken.getUserId();
            user = twitter.showUser(userID);

        } catch (TwitterException e1) {
            e1.printStackTrace();
        }

        return user;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        loginView.LoginSuccess(user,aToken);
    }

}
