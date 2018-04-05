package com.nadernabil.simpletwitterclient.UI.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nadernabil.simpletwitterclient.Adapters.FollowersAdapter;
import com.nadernabil.simpletwitterclient.Bases.FollowersContract;
import com.nadernabil.simpletwitterclient.CallBacks.OnLoadMoreListener;
import com.nadernabil.simpletwitterclient.Model.Objects.Follower;
import com.nadernabil.simpletwitterclient.Presenters.FollowersListPresenter;
import com.nadernabil.simpletwitterclient.R;
import com.nadernabil.simpletwitterclient.UI.Activities.MainActivity;
import com.nadernabil.simpletwitterclient.Utils.GMethods;
import com.nadernabil.simpletwitterclient.Utils.StorageUtil;

import java.util.ArrayList;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class FollowersList extends Fragment implements FollowersContract.FollowersView, SwipeRefreshLayout.OnRefreshListener {
    private ProgressBar progressBar;
    private TextView tv_emptyData;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<Follower> followers;
    private FollowersAdapter adapter;
    FollowersListPresenter presenter;
    Long Curser = Long.valueOf(-1);
    boolean IsActive = false, IsConnected = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IsConnected = GMethods.isNetworkAvailable(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.followers_frag_layout, container, false);
        InitViews(view);
        presenter = new FollowersListPresenter(this, StorageUtil.getInstance().doStuff(getActivity()).GetUID(), getActivity());
        return view;
    }

    @Override
    public void InitViews(View view) {
        progressBar = view.findViewById(R.id.progress_bar);
        tv_emptyData = view.findViewById(R.id.txt_no_available_data);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        followers = new ArrayList<>();
        adapter = new FollowersAdapter(getActivity(), followers, recyclerView, new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (Curser != Long.valueOf(-1)) {
                    followers.add(null);
                    adapter.notifyItemInserted(followers.size() - 1);
                    presenter.GetData(Curser);
                } else {
                    adapter.setLoaded();
                }
            }
        });

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.GetData(Curser);

    }

    @Override
    public void SetDataFirstTime(ArrayList<Follower> followers, Long curser) {
        progressBar.setVisibility(View.GONE);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (followers.size() == 0) {
            this.Curser = curser;
            this.followers.remove(this.followers.size() - 1);
            adapter.notifyItemRemoved(this.followers.size());
            adapter.setOnLoadMoreListener(null);
            return;
        }

        this.Curser = curser;
        this.followers.clear();
        this.followers = followers;
        adapter.ClearData();
        adapter.setItems(followers, false);
    }

    @Override
    public void SetReloadedData(ArrayList<Follower> followers, Long curser) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (followers.size() == 0) {
            this.Curser = curser;
            this.followers.remove(this.followers.size() - 1);
            adapter.notifyItemRemoved(this.followers.size());
            adapter.setOnLoadMoreListener(null);
            return;
        }
        this.Curser = curser;
        this.followers.remove(this.followers.size() - 1);
        adapter.notifyItemRemoved(this.followers.size());
        ArrayList<Follower> list = new ArrayList<>();
        list.addAll(followers);
        adapter.setItems(list, true);
        adapter.setLoaded();
    }

    @Override
    public void ShowEmptyData() {
        progressBar.setVisibility(View.GONE);
        tv_emptyData.setVisibility(View.VISIBLE);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public boolean IsOnLine() {
        return IsConnected;
    }

    @Override
    public void onStart() {
        super.onStart();
        IsActive = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        IsActive = false;
    }


    @Override
    public void onRefresh() {
        presenter.GetData(Curser);
    }
}
