package com.xyj.hecn.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.tencent.mmkv.MMKV;
import com.xyj.hecn.R;
import com.xyj.hecn.config.Keys;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        String mqttAddr = MMKV.defaultMMKV().decodeString(Keys.MMKV_KEY_MQTT_ADDRESS);
//        if (TextUtils.isEmpty(mqttAddr)) {
//            startActivity(new Intent(this, SettingActivity.class));
//        }else {
//            startActivity(new Intent(this, MainActivity.class));
//        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}