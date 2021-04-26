package com.xyj.hecn;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * null.java
 * description: TODO
 *
 * @author : Licy
 * @date : 2021/4/9
 * email ：licy3051@qq.com
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
    }
}
