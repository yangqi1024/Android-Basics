package com.itpiggy.android_basics.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itpiggy.android_basics.mvvm.model.LoginCallback;
import com.itpiggy.android_basics.mvvm.model.LoginModel;

public class LoginViewModel extends AndroidViewModel {
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    private MutableLiveData<Boolean> loginResult = new MutableLiveData<>();
    private final LoginModel loginModel;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginModel = new LoginModel();
    }

    public LiveData<Boolean> loginResultLiveData() {
        return loginResult;
    }

    public void login() {
        loginModel.login(username.get(), password.get(), new LoginCallback() {
            @Override
            public void onSuccess() {
                loginResult.postValue(true);
            }

            @Override
            public void onError(String message) {
                loginResult.postValue(false);
            }
        });
    }
}
