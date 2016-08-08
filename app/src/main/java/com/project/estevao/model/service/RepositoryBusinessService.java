package com.project.estevao.apigit.model.service;

import com.project.estevao.apigit.model.entity.Repository;
import com.project.estevao.apigit.model.entity.User;
import com.project.estevao.apigit.model.persistence.RepositoryRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Estevao on 08/08/2016.
 */
public class RepositoryBusinessService {
    public static void save(Repository repository) {
        List<Repository> byIdWeb = RepositoryRepository.findByIdWeb(repository.getIdWeb());
        if (!byIdWeb.isEmpty())
            repository.setId(byIdWeb.get(0).getId());
        RepositoryRepository.save(repository);
    }

    public static ArrayList<Repository> findAllByIdUser(User user) {
        return RepositoryRepository.getByIdUser(user.getId());
    }

}
