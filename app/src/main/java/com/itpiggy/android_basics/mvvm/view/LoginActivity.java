package com.itpiggy.android_basics.mvvm.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.itpiggy.android_basics.R;
import com.itpiggy.android_basics.databinding.ActivityMvvmBinding;
import com.itpiggy.android_basics.mvvm.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);

        ViewModelProvider.AndroidViewModelFactory modelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        LoginViewModel loginViewModel = modelFactory.create(LoginViewModel.class);
        dataBinding.setLoginViewModel(loginViewModel);

        dataBinding.btnLogin.setOnClickListener(v -> loginViewModel.login());
        loginViewModel.loginResultLiveData().observe(this, loginSuccess -> {
            if (loginSuccess) {
                onSuccess();
            } else {
                onError();
            }
        });
    }

    public void onSuccess() {
        //TODO 处理登录成功
    }

    public void onError() {
        //TODO 处理登录失败
    }
}