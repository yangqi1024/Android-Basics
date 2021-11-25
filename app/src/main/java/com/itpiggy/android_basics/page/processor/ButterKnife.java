package com.itpiggy.android_basics.page.processor;

import android.app.Activity;

public class ButterKnife {
    public static final String ACTIVITY_DELEGATE_SUFFIX = "DelegateBinder";
    public static void bind(Activity activity){
        //1、获取全限定类名
        String name = activity.getClass().getName();
        try {
            //2、 根据全限定类名获取通过注解解释器生成的Java类
            Class<?> clazz = Class.forName(name + ACTIVITY_DELEGATE_SUFFIX);
            //3、 通过反射获取构造方法并创建实例完成依赖注入
            clazz.getConstructor(activity.getClass()).newInstance(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
