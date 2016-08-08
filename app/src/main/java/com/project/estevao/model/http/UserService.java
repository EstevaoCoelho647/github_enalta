package com.project.estevao.apigit.model.http;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.project.estevao.apigit.ApiGitManagerApplication;
import com.project.estevao.apigit.controller.activity.SplashActivity;
import com.project.estevao.apigit.model.entity.User;
import com.project.estevao.apigit.util.ApplicationUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Estevao on 07/08/2016.
 */
public class UserService {

    private static final String URL_USER = "https://api.github.com/users";


    public static ArrayList<User> getUsersByWeb() {
        ArrayList<User> users = null;
        try {
            java.net.URL url = new URL(URL_USER);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.i("getUsersByWeb", "return of req: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, User.class);

                users = objectMapper.readValue(inputStream, collectionType);
                for (User user : users) {
                    byte[] bytes = Glide.with(ApplicationUtil.getContext()).
                            load(user.getAvatar()).
                            asBitmap().
                            toBytes().
                            into(100, 100).
                            get();
                    user.setAvatarBytes(bytes);
                }
            }

        } catch (Exception e) {
            Log.e(UserService.class.getName(), e.getMessage());
        }

        return users;
    }
}
