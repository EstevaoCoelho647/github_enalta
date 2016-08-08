package com.project.estevao.apigit.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.estevao.apigit.model.entity.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Estevao on 08/08/2016.
 */
public class RepositoryRepository {

    public static void save(Repository repository) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = RepositoryContract.getContentValues(repository);

        if (repository.getId() == null) {
            db.insert(RepositoryContract.TABLE, null, values);
        } else {
            String where = RepositoryContract.IDWEB + " = ?";
            String[] params = {String.valueOf(repository.getIdWeb())};
            db.update(RepositoryContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static List<Repository> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(RepositoryContract.TABLE, RepositoryContract.columns, null, null, null, null, RepositoryContract.ID);
        List<Repository> values = RepositoryContract.getRepositories(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static List<Repository> findByIdWeb(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = RepositoryContract.IDWEB + " = ?";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(RepositoryContract.TABLE, RepositoryContract.columns, where, params, null, null, null);
        List<Repository> values = RepositoryContract.getRepositories(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static ArrayList<Repository> getByIdUser(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = RepositoryContract.IDUSER + " = ?";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(RepositoryContract.TABLE, RepositoryContract.columns, where, params, null, null, null);
        ArrayList<Repository> values = RepositoryContract.getRepositories(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }
}
