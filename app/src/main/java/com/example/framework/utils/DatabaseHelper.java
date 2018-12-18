package com.example.framework.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.framework.lista.Model.Business.TaskBusiness;
import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.app.TaskApplication;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String databaseName = "taskDB";
    private static final int databaseVersion = 1;
    private static final Context context = TaskApplication.getInstance().getApplicationContext();

    public DatabaseHelper() {
        super(context, databaseName, null, databaseVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sd, ConnectionSource cs) {

        try {
            TableUtils.createTable(cs, Task.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
