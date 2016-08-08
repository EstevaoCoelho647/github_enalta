package com.project.estevao.apigit.model.asynctask;

import android.app.Activity;
import android.os.AsyncTask;

import com.project.estevao.apigit.controller.activity.SplashActivity;
import com.project.estevao.apigit.model.entity.Repository;
import com.project.estevao.apigit.model.entity.User;
import com.project.estevao.apigit.model.http.RepositoryService;
import com.project.estevao.apigit.model.service.RepositoryBusinessService;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Estevao on 08/08/2016.
 */
public class AsyncFindRepositories extends AsyncTask<Object, String, Void> {
    Activity context;
    SyncInterface activity;


    public AsyncFindRepositories(Activity context, SyncInterface activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Object... params) {
        for (int i = 0; i < params.length; i++) {
            ArrayList<Repository> repositoryByWeb = RepositoryService.getRepositoryByWeb((User)params[i]);
            if (repositoryByWeb != null && !repositoryByWeb.isEmpty())
                for (Repository repository : repositoryByWeb) {
                    repository.setIdUser(((User)params[i]).getId());
                    RepositoryBusinessService.save(repository);
                }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void repositories) {
        activity.onSuccess();
        super.onPostExecute(repositories);
    }

}
