package com.itpiggy.android_basics.model;

public class MainItemModel {
   private String title;
    private  Class className;
    public MainItemModel(String title,Class className){
        this.title = title;
        this.className = className;
    }

    public String getTitle() {
        return title;
    }

    public Class getClassName() {
        return className;
    }
}
