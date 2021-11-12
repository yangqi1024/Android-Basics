package com.itpiggy.android_basics.mvp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.itpiggy.android_basics.R;
import com.itpiggy.android_basics.mvp.presenter.ILoginPresenter;
import com.itpiggy.android_basics.mvp.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_login);
        AppCompatEditText etUserName = findViewById(R.id.et_username);
        AppCompatEditText etPassword = findViewById(R.id.et_password);
        AppCompatButton btnLogin = findViewById(R.id.btn_login);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
        btnLogin.setOnClickListener(v -> {
            handleLogin(etUserName.getText().toString(), etPassword.getText().toString());
        });
    }

    private void handleLogin(String userName, String password) {
        loginPresenter.login(userName, password);
    }

    @Override
    public void showLoading() {
        //TODO 展示加载框
    }

    @Override
    public void hideLoading() {
        //TODO 隐藏加载框
    }

    @Override
    public void onSuccess() {
        //TODO 处理登录成功
    }

    @Override
    public void onError(String message) {
        //TODO 处理登录失败
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }
}
