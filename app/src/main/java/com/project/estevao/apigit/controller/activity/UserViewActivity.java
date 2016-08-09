package com.project.estevao.apigit.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.project.estevao.apigit.R;
import com.project.estevao.apigit.controller.adapter.RepositoryListAdapter;
import com.project.estevao.apigit.controller.adapter.UserListAdapter;
import com.project.estevao.apigit.model.entity.Repository;
import com.project.estevao.apigit.model.entity.User;

import java.util.List;

/**
 * Created by estevao on 08/08/16.
 */
public class UserViewActivity extends AppCompatActivity {

    private User user;
    TextView txtViewUserName;
    TextView txtViewUserRepository;
    ImageView imgViewUserImage;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        bindExtras();
        setTitle("Repositories");
        bindEdtTxt();
        setUserData();
        bindRecycleView();

    }


    private void setUserData() {
        txtViewUserName.setText(user.getLogin());
        txtViewUserRepository.setText(user.getRepositoryUrl());

        Glide.with(UserViewActivity.this)
                .load(user.getAvatarBytes())
                .error(R.drawable.no_image)
                .placeholder(R.drawable.no_image)
                .centerCrop()
                .into(imgViewUserImage);

    }

    private void bindRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_repository);
        LinearLayoutManager llm = new LinearLayoutManager(UserViewActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        setRepositoryList(user);
    }

    private void setRepositoryList(User user) {
        RepositoryListAdapter adapter = new RepositoryListAdapter(UserViewActivity.this, user);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void bindEdtTxt() {
        txtViewUserName = (TextView) findViewById(R.id.user_name);
        txtViewUserRepository = (TextView) findViewById(R.id.user_repository);
        imgViewUserImage = (ImageView) findViewById(R.id.user_img);
    }

    private void bindExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getParcelable(UserListActivity.USER_IDN);
        }
    }
}
