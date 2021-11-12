package com.itpiggy.android_basics.mvc.model;

public interface LoginCallback {
    void onSuccess();

    void onError(String message);
}
