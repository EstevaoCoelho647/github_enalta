package com.project.estevao.apigit.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.project.estevao.apigit.R;
import com.project.estevao.apigit.controller.adapter.UserListAdapter;
import com.project.estevao.apigit.model.entity.User;
import com.project.estevao.apigit.model.service.UserBusinessService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Estevao on 07/08/2016.
 */
public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<User> users;
    public static String USER_IDN = "USER";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        getUsers();
        bindToolbar();
        bindRecycleView();

    }

    private void getUsers() {
        users = UserBusinessService.findAll();
    }

    private void bindToolbar() {
        setTitle("User List");
    }

    private void bindRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager llm = new LinearLayoutManager(UserListActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        setUserList(users);
    }

    private void setUserList(List<User> users) {
        UserListAdapter adapter = new UserListAdapter(UserListActivity.this, users) {
            @Override
            public void onItemCLick(User user) {
                Intent intent = new Intent(UserListActivity.this, UserViewActivity.class);
                intent.putExtra(USER_IDN, user);
                startActivity(intent);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
