package com.skillseeds.theacquits.Models;

import androidx.annotation.NonNull;

public class trainingModel {

    private String id;
    private String title;
    private String description;
    private String iconUrl;

    public trainingModel(String id, String title, String description, String iconUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: "+id + " Title: "+ title+ " Description: "+ description + " IconUrl: "+iconUrl;
    }
}
