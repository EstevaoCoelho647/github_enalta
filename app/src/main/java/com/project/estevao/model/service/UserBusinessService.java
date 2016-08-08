package com.project.estevao.apigit.model.service;

import com.project.estevao.apigit.controller.activity.SplashActivity;
import com.project.estevao.apigit.model.asynctask.AsyncFindRepositories;
import com.project.estevao.apigit.model.entity.Repository;
import com.project.estevao.apigit.model.entity.User;
import com.project.estevao.apigit.model.http.UserService;
import com.project.estevao.apigit.model.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Estevao on 07/08/2016.
 */
public class UserBusinessService {

    public static void save(User user) {
        List<User> byIdWeb = UserRepository.findByIdWeb(user.getIdWeb());
        if (!byIdWeb.isEmpty() && byIdWeb.get(0).getId() != null)
            user.setId(byIdWeb.get(0).getId());
        UserRepository.save(user);
    }

    public static List<User> findAll() {
        return UserRepository.getAll();
    }

    public static void sinchronizeFromWeb(ArrayList<User> users) {
        if (users != null)
            for (User p : users) {
                UserBusinessService.save(p);
            }
    }


}
