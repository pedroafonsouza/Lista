package com.example.framework.lista.Model.DTO;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {



    private String name;
    private String description;
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
