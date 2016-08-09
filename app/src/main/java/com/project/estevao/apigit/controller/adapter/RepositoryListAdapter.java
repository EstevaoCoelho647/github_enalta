package com.project.estevao.apigit.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.estevao.apigit.R;
import com.project.estevao.apigit.controller.activity.UserViewActivity;
import com.project.estevao.apigit.model.entity.Repository;
import com.project.estevao.apigit.model.entity.User;

/**
 * Created by estevao on 08/08/16.
 */
public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListAdapter.MyViewHolder> {
    private User user;
    private Context context;

    public RepositoryListAdapter(UserViewActivity userViewActivity, User user) {
        this.context = userViewActivity;
        this.user = user;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.item_repository, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Repository repository = user.getRepositories().get(position);
        holder.txtViewRepositoryName.setText(repository.getName());
        holder.txtStargazers.setText(String.valueOf(repository.getStargazers()));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewRepositoryName;
        TextView txtStargazers;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtViewRepositoryName = (TextView) itemView.findViewById(R.id.repository_name);
            txtStargazers = (TextView) itemView.findViewById(R.id.stargazers);
        }
    }

    @Override
    public int getItemCount() {
        return user.getRepositories().size();
    }

}
