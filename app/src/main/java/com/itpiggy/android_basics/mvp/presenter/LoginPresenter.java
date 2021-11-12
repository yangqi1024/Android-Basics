package com.itpiggy.android_basics.mvp.presenter;

import com.itpiggy.android_basics.mvp.model.ILoginModel;
import com.itpiggy.android_basics.mvp.model.LoginCallback;
import com.itpiggy.android_basics.mvp.model.LoginModel;
import com.itpiggy.android_basics.mvp.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {
    private ILoginView loginView;
    private final ILoginModel loginModel;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    @Override
    public void attachView(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void detachView() {
        loginView = null;
    }

    @Override
    public void login(String userName, String password) {
        loginModel.login(userName, password, new LoginCallback() {
            @Override
            public void onSuccess() {
                if (loginView != null) {
                    loginView.onSuccess();
                }
            }

            @Override
            public void onError(String message) {
                if (loginView != null) {
                    loginView.onError(message);
                }
            }
        });
    }
}
