package com.xyj.hecn.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.hjq.bar.OnTitleBarListener;
import com.tencent.mmkv.MMKV;
import com.xyj.hecn.R;
import com.xyj.hecn.config.Keys;
import com.xyj.hecn.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {

    private ActivitySettingBinding mSettingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mSettingBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);

        mSettingBinding.titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View view) {
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onTitleClick(View view) {

            }

            @Override
            public void onRightClick(View view) {
                saveConfigs();
            }
        });

        String mqttAddr = MMKV.defaultMMKV().decodeString(Keys.MMKV_KEY_MQTT_ADDRESS);
        mSettingBinding.etMqttAddress.setText(mqttAddr);

        String departCode = MMKV.defaultMMKV().decodeString(Keys.MMKV_KEY_DEPART_CODE);
        mSettingBinding.etDepartCode.setText(departCode);

        String roomCode = MMKV.defaultMMKV().decodeString(Keys.MMKV_KEY_ROOM_CODE);
        mSettingBinding.etRoomCode.setText(roomCode);
    }

    private void saveConfigs() {
        String mqttAddr = mSettingBinding.etMqttAddress.getText().toString();
        if (TextUtils.isEmpty(mqttAddr)) {
            ToastUtils.showShort("mqtt 服务地址不能为空~");
            return;
        } else {
            MMKV.defaultMMKV().encode(Keys.MMKV_KEY_MQTT_ADDRESS, mqttAddr);
        }

        String departCode = mSettingBinding.etDepartCode.getText().toString();
        if (TextUtils.isEmpty(departCode)) {
            ToastUtils.showShort("科室编码不能为空~");
            return;
        } else {
            MMKV.defaultMMKV().encode(Keys.MMKV_KEY_DEPART_CODE, departCode);
        }

        String roomCode = mSettingBinding.etRoomCode.getText().toString();
        if (TextUtils.isEmpty(roomCode)) {
            ToastUtils.showShort("诊室编码不能为空~");
            return;
        } else {
            MMKV.defaultMMKV().encode(Keys.MMKV_KEY_ROOM_CODE, roomCode);
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}