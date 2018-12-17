package com.example.framework.lista.Model.DTO;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;


@DatabaseTable(tableName = "task")
public class Task implements Serializable {



    @DatabaseField(generatedId = true)
    private String id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String description;

    @DatabaseField
    private Date date;


    public Task(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Task() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
