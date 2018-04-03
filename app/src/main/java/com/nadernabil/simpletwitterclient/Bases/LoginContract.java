package com.nadernabil.simpletwitterclient.Bases;

import android.content.Context;

import twitter4j.Twitter;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public interface LoginContract {
    interface LoginView {
        void LoginSuccess(User user, AccessToken accessToken);
        void InitView();
        void LogInClick();
        void Checker();
        void SetPresenter(LoginPresenter loginPresenter);

    }

    interface LoginPresenter{
        void StartLoginTask( RequestToken requestToken, Twitter twitter);
        void InsertUserIntoDb(User user , Context context);
    }


}
