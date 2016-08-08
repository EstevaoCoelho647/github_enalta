package com.project.estevao.apigit.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.project.estevao.apigit.model.entity.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Estevao on 08/08/2016.
 */
public class RepositoryContract {
    public static final String TABLE = "repositorytable";
    public static final String ID = "id";
    public static final String IDWEB = "idweb";
    public static final String IDUSER = "iduser";
    public static final String NAME = "name";
    public static final String STARGAZERS = "stargazers";

    public static final String[] columns = {ID, IDWEB,IDUSER, NAME, STARGAZERS};


    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(IDWEB + " INTEGER, ");
        create.append(IDUSER + " INTEGER, ");
        create.append(NAME + " TEXT, ");
        create.append(STARGAZERS + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Repository repository) {
        ContentValues values = new ContentValues();

        values.put(RepositoryContract.ID, repository.getId());
        values.put(RepositoryContract.IDWEB, repository.getIdWeb());
        values.put(RepositoryContract.IDUSER, repository.getIdUser());
        values.put(RepositoryContract.NAME, repository.getName());
        values.put(RepositoryContract.STARGAZERS, repository.getStargazers());

        return values;
    }

    static Repository getRepository(Cursor cursor) {
        Repository repository = new Repository();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            repository.setId(cursor.getLong(cursor.getColumnIndex(RepositoryContract.ID)));
            repository.setIdWeb(cursor.getLong(cursor.getColumnIndex(RepositoryContract.IDWEB)));
            repository.setIdUser(cursor.getLong(cursor.getColumnIndex(RepositoryContract.IDUSER)));
            repository.setName(cursor.getString(cursor.getColumnIndex(RepositoryContract.NAME)));
            repository.setStargazers(cursor.getInt(cursor.getColumnIndex(RepositoryContract.STARGAZERS)));

            return repository;
        }
        return null;
    }


    public static ArrayList<Repository> getRepositories(Cursor cursor) {
        ArrayList<Repository> repositories = new ArrayList<>();
        while (cursor.moveToNext()) {
            repositories.add(getRepository(cursor));
        }
        return repositories;
    }
}
