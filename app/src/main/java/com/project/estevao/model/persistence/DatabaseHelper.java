package com.project.estevao.apigit.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.estevao.apigit.model.entity.Repository;
import com.project.estevao.apigit.util.ApplicationUtil;

/**
 * Created by Estevao on 07/08/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "apiGit";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(){
        return new DatabaseHelper(ApplicationUtil.getContext());
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserContract.getCreateTableScript());
        db.execSQL(RepositoryContract.getCreateTableScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
