package com.slack.animationplayground;

import android.support.v7.app.AppCompatActivity;

public class Example {
    private String title;
    private String description;
    private Class<? extends AppCompatActivity> activityClass;

    public Example(String title, String description, Class<? extends AppCompatActivity> activityClass) {
        this.title = title;
        this.description = description;
        this.activityClass = activityClass;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Class<? extends AppCompatActivity> getActivityClass() {
        return activityClass;
    }
}
