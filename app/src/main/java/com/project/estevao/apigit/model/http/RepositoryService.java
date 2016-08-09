package com.project.estevao.apigit.model.http;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.project.estevao.apigit.controller.activity.SplashActivity;
import com.project.estevao.apigit.model.entity.Repository;
import com.project.estevao.apigit.model.entity.User;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Estevao on 08/08/2016.
 */
public class RepositoryService {

    private static String URL_USER;

    public static ArrayList<Repository> getRepositoryByWeb(User user) {
        URL_USER = user.getRepositoryUrl();
        ArrayList<Repository> repositorys = null;
        try {
            java.net.URL url = new URL(URL_USER);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.i("getRepositoryByWeb", "return of req: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Repository.class);

                repositorys = objectMapper.readValue(inputStream, collectionType);
            }

        } catch (Exception e) {
            Log.e(UserService.class.getName(), e.getMessage());
        }

        return repositorys;
    }
}
