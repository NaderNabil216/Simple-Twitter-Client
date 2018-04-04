package com.nadernabil.simpletwitterclient.Presenters;

import android.content.Context;

import com.nadernabil.simpletwitterclient.Bases.ProfileContract;
import com.nadernabil.simpletwitterclient.Model.DataBase.UserAccountsOperations;
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


}
