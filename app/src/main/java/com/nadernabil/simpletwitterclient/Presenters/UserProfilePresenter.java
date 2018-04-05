package com.nadernabil.simpletwitterclient.Presenters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.nadernabil.simpletwitterclient.Bases.ProfileContract;
import com.nadernabil.simpletwitterclient.Model.DataBase.FollowersOperations;
import com.nadernabil.simpletwitterclient.Model.DataBase.TweetsOperations;
import com.nadernabil.simpletwitterclient.Model.DataBase.UserAccountsOperations;
import com.nadernabil.simpletwitterclient.R;
import com.nadernabil.simpletwitterclient.UI.Activities.LoginActivity;
import com.nadernabil.simpletwitterclient.Utils.StorageUtil;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class UserProfilePresenter implements ProfileContract.profile_Presenter {
    private Context context;
    private UserAccountsOperations userAccountsOperations;
    private Long User_Id;
    private ProfileContract.profile_view view;

    public UserProfilePresenter(Context context, Long user_Id, ProfileContract.profile_view view) {
        this.context = context;
        User_Id = user_Id;
        this.view = view;
        userAccountsOperations = new UserAccountsOperations(context);
    }

    @Override
    public void GetData() {
        GetAllUsers();
        GetUser();
    }

    @Override
    public void GetUser() {
        view.SetDataCurrentUser(userAccountsOperations.getUser(User_Id));
    }

    @Override
    public void GetAllUsers() {
        view.SetDataUsers(userAccountsOperations.getAllData());
    }

    @Override
    public void SwitchAccounts(Long choosen_account_id) {
        StorageUtil.getInstance().doStuff(context).SetUID(choosen_account_id);
        this.User_Id = choosen_account_id;
        GetData();
    }

    @Override
    public void PerformLogOut(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getString(R.string.twitter));
        progressDialog.setMessage(context.getString(R.string.logging_out));
        progressDialog.setCancelable(false);
        progressDialog.show();

        FollowersOperations followersOperations = new FollowersOperations(context);
        TweetsOperations tweetsOperations = new TweetsOperations(context);
        userAccountsOperations.DeleteAccounts();
        followersOperations.DeleteFollowers();
        tweetsOperations.DeleteTweets();
        StorageUtil.getInstance().doStuff(context).clearCached();
        StorageUtil.getInstance().doStuff(context).SetIsLogged(false);
        context.startActivity(new Intent(context, LoginActivity.class));
        progressDialog.dismiss();
    }


}
