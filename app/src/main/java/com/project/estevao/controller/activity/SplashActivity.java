package com.project.estevao.apigit.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.estevao.apigit.R;
import com.project.estevao.apigit.model.asynctask.AsyncFindAllUser;
import com.project.estevao.apigit.model.asynctask.AsyncFindRepositories;
import com.project.estevao.apigit.model.asynctask.SyncInterface;
import com.project.estevao.apigit.model.entity.Repository;
import com.project.estevao.apigit.model.entity.User;
import com.project.estevao.apigit.model.persistence.UserRepository;
import com.project.estevao.apigit.model.service.RepositoryBusinessService;
import com.project.estevao.apigit.model.service.UserBusinessService;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity implements SyncInterface {

    private LinearLayout buttonUpdate;
    private LinearLayout buttonEnter;
    private ProgressBar progress;
    private TextView txtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        bindLoadItens();
        bindOptions();
    }

    private void bindLoadItens() {
        progress = (ProgressBar) findViewById(R.id.progress);
        txtView = (TextView) findViewById(R.id.text);
    }

    private void bindOptions() {
        buttonEnter = (LinearLayout) findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserRepository.getAll() == null || UserRepository.getAll().size() > 0)
                    startListUserActivity();
                else
                    Toast.makeText(SplashActivity.this, "Database empty, please update.", Toast.LENGTH_SHORT).show();
            }
        });
        buttonUpdate = (LinearLayout) findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initUserAsync();
            }
        });

    }

    private void initUserAsync() {
        setItemsVisibility(View.VISIBLE);
        AsyncFindAllUser findAll = new AsyncFindAllUser(SplashActivity.this, SplashActivity.this);
        findAll.execute();
    }

    private void setItemsVisibility(int state) {
        if (state == View.VISIBLE) {
            buttonEnter.setVisibility(View.GONE);
            buttonUpdate.setVisibility(View.GONE);
            progress.setVisibility(View.VISIBLE);
            txtView.setVisibility(View.VISIBLE);
        } else {
            buttonEnter.setVisibility(View.VISIBLE);
            buttonUpdate.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
            txtView.setVisibility(View.GONE);
        }

    }

    @Override
    public void sinchronizeUser() {
        List<User> users = UserRepository.getAll();
        if (users != null && !users.isEmpty()) {
            AsyncFindRepositories asyncFindRepositories = new AsyncFindRepositories(this, this);
            asyncFindRepositories.execute(users.toArray());
        } else {
            Toast.makeText(SplashActivity.this, "Connection error.\nPlease verify your connection", Toast.LENGTH_SHORT).show();
            setItemsVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(SplashActivity.this, "Update success", Toast.LENGTH_SHORT).show();
        startListUserActivity();
    }

    private void startListUserActivity() {
        Intent intent = new Intent(SplashActivity.this, UserListActivity.class);
        startActivity(intent);
        finish();
    }

}
