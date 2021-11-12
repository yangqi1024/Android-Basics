package com.itpiggy.android_basics.mvp.presenter;

import com.itpiggy.android_basics.mvp.view.ILoginView;

public interface ILoginPresenter {
    void attachView(ILoginView loginView);

    void detachView();

    void login(String userName, String password);
}
