package com.project.estevao.apigit.model.asynctask;

import com.project.estevao.apigit.model.entity.Repository;
import com.project.estevao.apigit.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Estevao on 07/08/2016.
 */
public interface SyncInterface {
    void sinchronizeUser();

    void onSuccess();

}
