package com.itpiggy.android_basics.mvvm.model;

public interface LoginCallback {
    void onSuccess();

    void onError(String message);
}
