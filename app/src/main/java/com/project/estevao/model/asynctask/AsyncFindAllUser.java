package com.project.estevao.apigit.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.project.estevao.apigit.controller.activity.SplashActivity;
import com.project.estevao.apigit.model.entity.Repository;
import com.project.estevao.apigit.model.entity.User;
import com.project.estevao.apigit.model.http.RepositoryService;
import com.project.estevao.apigit.model.http.UserService;
import com.project.estevao.apigit.model.service.RepositoryBusinessService;
import com.project.estevao.apigit.model.service.UserBusinessService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Estevao on 07/08/2016.
 */
public class AsyncFindAllUser extends AsyncTask<Activity, String, ArrayList<User>> {
    ProgressDialog pd;
    Activity context;
    SyncInterface activity;

    public AsyncFindAllUser(Activity context, SyncInterface activity) {
        this.context = context;
        this.activity = activity;

    }

    @Override
    protected ArrayList<User> doInBackground(Activity... params) {
        return UserService.getUsersByWeb();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<User> users) {
        UserBusinessService.sinchronizeFromWeb(users);
        activity.sinchronizeUser();
        super.onPostExecute(users);
    }
}
