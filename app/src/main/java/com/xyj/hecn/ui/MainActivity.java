package com.xyj.hecn.ui;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mmkv.MMKV;
import com.xyj.hecn.R;
import com.xyj.hecn.adapter.LinePersonRvAdapter;
import com.xyj.hecn.config.Keys;
import com.xyj.hecn.databinding.ActivityMainBinding;
import com.xyj.hecn.event.MqttMessageEvent;
import com.xyj.hecn.service.MessageService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Licy
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;
    private MainViewModel mMainViewModel;
    private ScheduledExecutorService mExecutorService;
    private Runnable mTimeTask;
    private LinePersonRvAdapter mRvAdapter;

    private int count = 4;
    private long duration = 1000;
    private long[] mHit = new long[count];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainBinding.setLifecycleOwner(this);
        mMainViewModel =
                new ViewModelProvider(this,
                        new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mMainBinding.setData(mMainViewModel.getPatientBeans().getValue());


        initPermission();

        initTime();

        initRv();

        MessageService.startService(this);

        mMainBinding.viewHideSetting.setOnClickListener(v -> {
            continueClick(count, duration);
        });
    }

    private void continueClick(int count, long time) {
        // 每次点击时，数组向前移动一位
        System.arraycopy(mHit, 1, mHit, 0, mHit.length - 1);
        // 为数组最后一位赋值
        mHit[mHit.length - 1] = System.currentTimeMillis();
        if (mHit[0] >= (System.currentTimeMillis() - time)) {
            mHit = new long[count];
            startActivity(new Intent(this, SettingActivity.class));
            finish();
        }
    }

    private void initRv() {
        mRvAdapter = new LinePersonRvAdapter(mMainViewModel.getPatientBeans().getValue().getData());
        mMainBinding.rvLinePersons.setLayoutManager(new LinearLayoutManager(this));
        mMainBinding.rvLinePersons.setAdapter(mRvAdapter);
        mRvAdapter.setEmptyView(R.layout.view_empty_for_rv_small);
    }

    private void initPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_PHONE_STATE}
                        , 1000);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}
                        , 1000);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            boolean granted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    granted = false;
                }
            }
            if (granted) {
                ToastUtils.showShort("权限申请成功~");
            } else {
                ToastUtils.showShort("权限申请失败~");
            }
        }
    }

    private void initTime() {
        mExecutorService = new ScheduledThreadPoolExecutor(1);
        mTimeTask = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMainBinding.tvTime.setText(parseTimeToDate());
                    }
                });
            }
        };
        mExecutorService.scheduleAtFixedRate(mTimeTask, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        if (mExecutorService != null) {
            mExecutorService.shutdown();
        }
        super.onDestroy();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMqttMsg(MqttMessageEvent event) {
        if (event != null) {
            if (event.mMqttMessageBean != null) {
//                MqttMessageBean value = mMainViewModel.getPatientBeans().getValue();
//                value.setDepartment(event.mMqttMessageBean.getDepartment());
//                value.getData().clear();
//
//                LogUtils.d(event.mMqttMessageBean.toString());
//                mMainBinding.tvDepartName.setText(event.mMqttMessageBean.getDepartment());
//                List<PatientBean> data = event.mMqttMessageBean.getData();
//                for (PatientBean datum : data) {
//                    if (datum.getCalled() == 1) {
//                        mMainBinding.tvCurrentPatientNumber.setText(datum.getNumber() + "   " + datum.getName());
//                    } else if (datum.getCalled() == 0) {
//                        value.getData().add(datum);
//                    }
//                }
//
//                if (value.getData().size() > 1) {
//                    mMainBinding.tvCurrentLineNumber.setText(getString(R.string.current_line_total_number, value.getData().size() - 1));
//                } else {
//                    mMainBinding.tvCurrentLineNumber.setText(getString(R.string.current_line_total_number, 0));
//                }
//
//                mMainViewModel.getPatientBeans().setValue(value);
//                mRvAdapter.notifyDataSetChanged();
            }
        }
    }


    /**
     * 把当前时间转为日期
     *
     * @return
     */
    public static String parseTimeToDate() {
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.getDefault());
        return fromFormat.format(new Date());
    }
}