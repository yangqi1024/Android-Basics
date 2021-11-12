package com.itpiggy.android_basics.mvvm.model;

public interface ILoginModel {
    void login(String userName, String password, LoginCallback callback);
}
