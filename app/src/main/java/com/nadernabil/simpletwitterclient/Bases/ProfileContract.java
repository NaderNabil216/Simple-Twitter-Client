package com.nadernabil.simpletwitterclient.Bases;

import android.content.Context;
import android.view.View;

import com.nadernabil.simpletwitterclient.Model.Objects.UserAccount;

import java.util.ArrayList;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public interface ProfileContract {

    interface profile_view {

        void InitViews(View view);

        void SetDataUsers(ArrayList<UserAccount> userAccounts);

        void SetDataCurrentUser(UserAccount account);

        void AddNewAccount();

        void Switch_Account(Long choosen_account_id);

        void LogOut();

        void ChangeLanguage();

    }

    interface profile_Presenter {

        void GetData();

        void GetUser();

        void GetAllUsers();

        void SwitchAccounts(Long choosen_account_id);

        void PerformLogOut(Context context);

    }
}
