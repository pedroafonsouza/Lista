package com.example.framework.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.framework.lista.Model.Business.TaskBusiness;
import com.example.framework.lista.Model.DTO.Task;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String databaseName = "taskDB";
    private static final int databaseVersion = 1;

    public DatabaseHelper(TaskBusiness context, String databaseName) {
        super(context, databaseName, null, databaseVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sd, ConnectionSource cs) {

        String sql = "CREATE TABLE " + databaseName +
                "( id INTEGER PRIMARY KEY," +
                " name TEXT UNIQUE NOT NULL," +
                " description TEXT," +
                " date TEXT" +
                ");";
        sd.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
