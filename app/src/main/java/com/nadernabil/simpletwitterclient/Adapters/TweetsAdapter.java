package com.nadernabil.simpletwitterclient.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nadernabil.simpletwitterclient.Model.Objects.Follower;
import com.nadernabil.simpletwitterclient.Model.Objects.Tweet;
import com.nadernabil.simpletwitterclient.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by NaderNabil@gmail.com on 4/4/2018.
 */

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    private ArrayList<Tweet> tweets;
    private Context context;
    private Follower follower;

    public TweetsAdapter(ArrayList<Tweet> tweets, Context context, Follower follower) {
        this.tweets = tweets;
        this.context = context;
        this.follower = follower;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.tweet_content.setText(tweet.getContent());
        holder.created_at.setText(tweet.getCreated_at());
        holder.user_name.setText(follower.getUser_name());
        holder.user_handle.setText(follower.getHandle());
        Picasso.with(context).load(follower.getProfile_image()).placeholder(R.drawable.twitter_default).into(holder.profile_image);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_image;
        TextView user_name, user_handle, tweet_content, created_at;

        public ViewHolder(View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            user_name = itemView.findViewById(R.id.txt_user_screen_name);
            user_handle = itemView.findViewById(R.id.txt_user_handle);
            tweet_content = itemView.findViewById(R.id.txt_tweet_content);
            created_at = itemView.findViewById(R.id.txt_created_at);
        }
    }
}
