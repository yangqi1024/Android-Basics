package com.itpiggy.android_basics.mvp.model;

public interface LoginCallback {
    void onSuccess();

    void onError(String message);
}
