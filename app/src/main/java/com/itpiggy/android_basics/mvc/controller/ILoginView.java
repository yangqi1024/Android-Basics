package com.itpiggy.android_basics.mvc.controller;

public interface ILoginView {
    void showLoading();

    void hideLoading();

    void onSuccess();

    void onError(String message);
}
