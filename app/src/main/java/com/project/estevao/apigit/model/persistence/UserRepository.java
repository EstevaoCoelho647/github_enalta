package com.project.estevao.apigit.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.estevao.apigit.model.entity.User;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Estevao on 07/08/2016.
 */
public class UserRepository {

    public static void save(User user) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = UserContract.getContentValues(user);

        if (user.getId() == null) {
            db.insert(UserContract.TABLE, null, values);
        } else {
            String where = UserContract.IDWEB + " = ?";
            String[] params = {String.valueOf(user.getIdWeb())};
            db.update(UserContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static List<User> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(UserContract.TABLE, UserContract.columns, null, null, null, null, UserContract.ID);
        List<User> values = UserContract.getUsers(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static List<User> findByIdWeb(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = UserContract.IDWEB + " = ?";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(UserContract.TABLE, UserContract.columns, where, params, null, null, null);
        List<User> values = UserContract.getUsers(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }


}
