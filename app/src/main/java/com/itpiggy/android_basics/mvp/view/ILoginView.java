package com.itpiggy.android_basics.mvp.view;

public interface ILoginView {
    void showLoading();

    void hideLoading();

    void onSuccess();

    void onError(String message);
}
