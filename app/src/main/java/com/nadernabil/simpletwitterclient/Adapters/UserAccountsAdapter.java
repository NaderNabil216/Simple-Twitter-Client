package com.nadernabil.simpletwitterclient.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nadernabil.simpletwitterclient.Bases.ProfileContract;
import com.nadernabil.simpletwitterclient.Model.Objects.UserAccount;
import com.nadernabil.simpletwitterclient.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by NaderNabil@gmail.com on 4/4/2018.
 */

public class UserAccountsAdapter extends RecyclerView.Adapter<UserAccountsAdapter.ViewHolder> {

    private ArrayList<UserAccount> accounts;
    private Context context;
    private Long Current_uid;
    private ProfileContract.profile_view view;

    public UserAccountsAdapter(ArrayList<UserAccount> accounts, ProfileContract.profile_view view, Context context, Long current_uid) {
        this.accounts = accounts;
        this.context = context;
        this.view = view;
        Current_uid = current_uid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user_account, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UserAccount userAccount = accounts.get(position);
        Picasso.with(context).load(userAccount.getProfile_image()).placeholder(R.drawable.twitter_default).into(holder.account_image);
        holder.account_name.setText(userAccount.getUser_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(userAccount.getId(), Current_uid)) {
                    Toast.makeText(context, R.string.same_Account, Toast.LENGTH_SHORT).show();
                } else {
                    UserAccountsAdapter.this.Current_uid = userAccount.getId();
                    view.Switch_Account(userAccount.getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView account_image;
        TextView account_name;

        public ViewHolder(View itemView) {
            super(itemView);
            account_image = itemView.findViewById(R.id.account_img);
            account_name = itemView.findViewById(R.id.txt_account_name);
        }
    }
}
