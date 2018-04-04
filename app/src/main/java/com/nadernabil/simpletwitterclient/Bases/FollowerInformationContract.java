package com.nadernabil.simpletwitterclient.Bases;

import com.nadernabil.simpletwitterclient.Model.Objects.Follower;
import com.nadernabil.simpletwitterclient.Model.Objects.Tweet;

import java.util.ArrayList;

import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.User;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public interface FollowerInformationContract {


    interface InfoView {

        void InitViews();

        void SetFollowerData();

        void SetData(ArrayList<Tweet> tweets);

        void ShowEmptyData();

        boolean IsOnLine();

    }

    interface InfoPresenter {

        void GetDataFromService();

        void GetDataFromLocalDB();

        void GetData();

        void InsertTweetsToDb (ResponseList<Status> statuses);

        void SetDataInView (ResponseList<Status> statuses);

        ArrayList<Tweet> ConvertStatusToTweet (ResponseList<Status> statuses);

        void ShowEmptyData();

    }

}
