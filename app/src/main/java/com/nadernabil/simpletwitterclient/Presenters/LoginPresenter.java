package com.nadernabil.simpletwitterclient.Presenters;

import android.content.Context;
import android.net.Uri;

import com.nadernabil.simpletwitterclient.AsyncTasks.LoginAsyncTask;
import com.nadernabil.simpletwitterclient.Bases.LoginContract;
import com.nadernabil.simpletwitterclient.Bases.LoginContract.LoginView;
import com.nadernabil.simpletwitterclient.Model.DataBase.UserAccountsOperations;
import com.nadernabil.simpletwitterclient.UI.Activities.LoginActivity;
import com.nadernabil.simpletwitterclient.Utils.GMethods;

import twitter4j.Twitter;
import twitter4j.User;
import twitter4j.auth.RequestToken;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class LoginPresenter implements LoginContract.LoginPresenter {
    LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginView.SetPresenter(this);
    }

    @Override
    public void StartLoginTask(RequestToken requestToken, Twitter twitter) {
        Uri uri = ((LoginActivity) loginView).getIntent().getData();
        if (uri != null && uri.toString().startsWith(GMethods.TWITTER_CALLBACK_URL)) {
            // oAuth verifier
            String verifier = uri.getQueryParameter(GMethods.URL_TWITTER_OAUTH_VERIFIER);
            /**
             * here start getting user data and token
             */
            LoginAsyncTask loginAsyncTask = new LoginAsyncTask(loginView, requestToken, twitter, verifier);
            loginAsyncTask.execute();
        }
    }

    @Override
    public void InsertUserIntoDb(User user , Context context) {
        UserAccountsOperations operations = new UserAccountsOperations(context);
        operations.insert_user(user);
    }


}
