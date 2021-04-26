package com.xyj.hecn.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xyj.hecn.bean.MqttMessageBean;
import com.xyj.hecn.bean.PatientBean;

import java.util.ArrayList;
import java.util.List;

/**
 * MainViewModel
 * description: TODO
 *
 * @author : Licy
 * @date : 2021/4/25
 * email ：licy3051@qq.com
 */
public class MainViewModel extends ViewModel {

    private MutableLiveData<MqttMessageBean> mPatientBeans;

    public MutableLiveData<MqttMessageBean> getPatientBeans() {
        if (mPatientBeans == null) {
            mPatientBeans = new MutableLiveData<>();
            mPatientBeans.setValue(new MqttMessageBean("", new ArrayList<>()));
        }
        return mPatientBeans;
    }

}
