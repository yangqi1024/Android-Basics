package com.itpiggy.android_basics.mvp.model;

public interface ILoginModel {
    void login(String userName, String password, LoginCallback callback);
}
