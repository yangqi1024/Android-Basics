package com.itpiggy.android_basics.mvc.model;

public interface ILoginModel {
    void login(String userName, String password, LoginCallback callback);
}
