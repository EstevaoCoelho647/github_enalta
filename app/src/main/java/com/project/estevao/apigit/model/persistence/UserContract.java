package com.project.estevao.apigit.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.project.estevao.apigit.model.entity.User;
import com.project.estevao.apigit.model.service.RepositoryBusinessService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Estevao on 07/08/2016.
 */
public class UserContract {

    public static final String TABLE = "usertable";
    public static final String ID = "id";
    public static final String IDWEB = "idweb";
    public static final String LOGIN = "login";
    public static final String AVATAR = "avatar";
    public static final String REPOSITORY_URL = "repositoryUrl";
    public static final String AVATAR_BYTES = "avatarBytes";


    public static final String[] columns = {ID, IDWEB, LOGIN, AVATAR, AVATAR_BYTES, REPOSITORY_URL};


    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(IDWEB + " INTEGER, ");
        create.append(LOGIN + " TEXT, ");
        create.append(AVATAR + " TEXT, ");
        create.append(AVATAR_BYTES + " BLOB, ");
        create.append(REPOSITORY_URL + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();

        values.put(UserContract.ID, user.getId());
        values.put(UserContract.IDWEB, user.getIdWeb());
        values.put(UserContract.LOGIN, user.getLogin());
        values.put(UserContract.AVATAR, user.getAvatar());
        values.put(UserContract.AVATAR_BYTES, user.getAvatarBytes());
        values.put(UserContract.REPOSITORY_URL, user.getRepositoryUrl());

        return values;
    }

    static User getUser(Cursor cursor) {
        User user = new User();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            user.setId(cursor.getLong(cursor.getColumnIndex(UserContract.ID)));
            user.setIdWeb(cursor.getLong(cursor.getColumnIndex(UserContract.IDWEB)));
            user.setLogin(cursor.getString(cursor.getColumnIndex(UserContract.LOGIN)));
            user.setAvatar(cursor.getString(cursor.getColumnIndex(UserContract.AVATAR)));
            user.setAvatarBytes(cursor.getBlob(cursor.getColumnIndex(UserContract.AVATAR_BYTES)));
            user.setRepositoryUrl(cursor.getString(cursor.getColumnIndex(UserContract.REPOSITORY_URL)));
            user.setRepositories(RepositoryBusinessService.findAllByIdUser(user));

            return user;
        }
        return null;
    }

    public static List getUsers(Cursor cursor) {
        ArrayList<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            users.add(getUser(cursor));
        }
        return users;
    }


}
