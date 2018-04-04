package com.nadernabil.simpletwitterclient.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nadernabil.simpletwitterclient.CallBacks.OnLoadMoreListener;
import com.nadernabil.simpletwitterclient.Model.Objects.Follower;
import com.nadernabil.simpletwitterclient.R;
import com.nadernabil.simpletwitterclient.UI.Activities.FollowerInformation;
import com.nadernabil.simpletwitterclient.Utils.GMethods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by NaderNabil@gmail.com on 4/3/2018.
 */

public class FollowersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //for load more
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private Context context;
    private ArrayList<Follower> followers;
    private boolean isLoading;
    private OnLoadMoreListener onLoadMoreListener;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public FollowersAdapter(Context context, ArrayList<Follower> followers, RecyclerView recyclerView, OnLoadMoreListener monLoadMoreListener) {
        this.context = context;
        this.followers = followers;
        this.onLoadMoreListener = monLoadMoreListener;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItem + visibleThreshold >= 5) {
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_follower, parent, false));
        } else if (viewType == VIEW_TYPE_LOADING) {
            return new ProgressViewHolder(LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            final Follower follower = followers.get(position);
            Picasso.with(context).load(follower.getProfile_image()).placeholder(R.drawable.twitter_default).into(((ItemViewHolder) holder).profile_image);

            ((ItemViewHolder) holder).user_name.setText(follower.getUser_name());
            ((ItemViewHolder) holder).user_handle.setText(follower.getHandle());
            ((ItemViewHolder) holder).user_bio.setText(follower.getBio());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FollowerInformation.class);
                    intent.putExtra(GMethods.FOLLOWER, GMethods.FOLLOWER_TO_STRING(follower));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return followers.size();
    }

    @Override
    public int getItemViewType(int position) {
        return followers.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setItems(ArrayList<Follower> followers, boolean IsAddToList) {
        if (!IsAddToList) {
            this.followers = followers;
            this.notifyDataSetChanged();
        } else {
            this.followers.addAll(followers);
            this.notifyItemRangeInserted(followers.size(), followers.size());
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        onLoadMoreListener = listener;
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_image;
        TextView user_name, user_handle, user_bio;

        public ItemViewHolder(View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            user_name = itemView.findViewById(R.id.txt_user_screen_name);
            user_handle = itemView.findViewById(R.id.txt_user_handle);
            user_bio = itemView.findViewById(R.id.txt_user_bio);
        }
    }

    private class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar1);
        }
    }
}
