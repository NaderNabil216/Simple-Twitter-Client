package com.nadernabil.simpletwitterclient.UI.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nadernabil.simpletwitterclient.Bases.LoginContract;
import com.nadernabil.simpletwitterclient.Presenters.LoginPresenter;
import com.nadernabil.simpletwitterclient.R;
import com.nadernabil.simpletwitterclient.Utils.GMethods;
import com.nadernabil.simpletwitterclient.Utils.StorageUtil;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {
    private Button btn_login;

    // Twitter
    private static Twitter twitter;
    private static RequestToken requestToken;
    private StorageUtil util;
    private LoginContract.LoginPresenter presenter;
    boolean FromMainActivity = false;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitView();
        CheckerForAddNewAccount();
    }

    @Override
    public void InitView() {
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInClick();
            }
        });

        util = StorageUtil.getInstance().doStuff(this);
        presenter = new LoginPresenter(this);

    }

    @Override
    public void LogInClick() {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(GMethods.TWITTER_CONSUMER_KEY);
        builder.setOAuthConsumerSecret(GMethods.TWITTER_CONSUMER_SECRET);
        Configuration configuration = builder.build();

        TwitterFactory factory = new TwitterFactory(configuration);
        twitter = factory.getInstance();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    requestToken = twitter.getOAuthRequestToken(GMethods.TWITTER_CALLBACK_URL);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL())));
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    /**
     * check whether user is logged in or , if he is coming with auth data or just opening the app , or coming from main activity to add new account
     */
    @Override
    public void CheckerForTwitterVerifier() {
        if (util.IsLogged() && !util.IsFromMainActivity()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            if (GMethods.isNetworkAvailable(this)) {
                presenter.StartLoginTask(requestToken, twitter);
            } else {
                Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * MVP
     */
    @Override
    public void SetPresenter(LoginContract.LoginPresenter loginPresenter) {
        presenter = loginPresenter;
    }

    @Override
    public void CheckerForAddNewAccount() {
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra(GMethods.From_Main_Activity, false)) {
            FromMainActivity = true;
            btn_login.setText(R.string.click_here_to_add_new_account);
        } else {
            CheckerForTwitterVerifier();
        }
    }

    @Override
    public void ShowProgressDialoug() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.twitter));
        progressDialog.setMessage(getString(R.string.logging_in));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * login granted
     */
    @Override
    public void LoginSuccess(User user, AccessToken accessToken) {
        util.SetIsLogged(true);
        util.SetUID(user.getId());
        progressDialog.dismiss();
        util.setFromMainActivity(false);
        presenter.InsertUserIntoDb(user, this);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (FromMainActivity) {
            finish();
            util.setFromMainActivity(false);
        } else {
            finishAffinity();
            System.exit(0);
        }

    }
}
