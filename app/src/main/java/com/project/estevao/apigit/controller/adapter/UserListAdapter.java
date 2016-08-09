package com.project.estevao.apigit.controller.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.project.estevao.apigit.R;
import com.project.estevao.apigit.controller.activity.UserListActivity;
import com.project.estevao.apigit.model.entity.User;
import com.project.estevao.apigit.model.service.UserBusinessService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Estevao on 07/08/2016.
 */
public abstract class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private Context context;
    private List<User> users;


    public UserListAdapter(Activity context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final User user = getItem(position);

        holder.txtViewUserName.setText(user.getLogin());
        holder.txtViewUserRepository.setText(user.getRepositoryUrl());

        Glide.with(context)
                .load(user.getAvatarBytes())
                .error(R.drawable.no_image)
                .placeholder(R.drawable.no_image)
                .centerCrop()
                .into(holder.imgViewUserImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemCLick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public User getItem(int position) {
        return users.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewUserName;
        TextView txtViewUserRepository;
        ImageView imgViewUserImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            LinearLayout linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            txtViewUserName = (TextView) linearLayout.findViewById(R.id.user_name);
            txtViewUserRepository = (TextView) linearLayout.findViewById(R.id.user_repository);
            imgViewUserImage = (ImageView) itemView.findViewById(R.id.user_img);
        }
    }

    public abstract void onItemCLick(User user);
}
