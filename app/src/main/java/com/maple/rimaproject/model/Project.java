package com.maple.rimaproject.model;

import android.content.Intent;

/**
 * Created by Khalid Aldaboubi on 4/3/2019 4:01 PM .
 * Maple Technologies Ltd
 * khalid.aldaboubi93@gmail.com
 * Project Name : royalev-app
 */
public class Project {
    private Integer id;
    private String name;
    private String details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
