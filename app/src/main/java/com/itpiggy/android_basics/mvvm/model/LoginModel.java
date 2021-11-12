package com.itpiggy.android_basics.mvvm.model;

public class LoginModel implements ILoginModel {

    @Override
    public void login(String userName, String password, LoginCallback callback) {
        //TODO 校验参数
        boolean isLogin = false;
        //TODO 请求服务器登录，并回调结果
        if (isLogin) {
            callback.onSuccess();
        } else {
            callback.onError("登录失败");
        }
    }
}
