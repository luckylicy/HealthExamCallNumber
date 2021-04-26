package com.xyj.hecn.event;

import com.xyj.hecn.bean.MqttMessageBean;
import com.xyj.hecn.bean.PatientBean;

import java.util.List;

/**
 * null.java
 * description: TODO
 *
 * @author : Licy
 * @date : 2021/4/26
 * email ：licy3051@qq.com
 */
public class MqttMessageEvent {

    /**
     *
     */
    public int type;
    public MqttMessageBean mMqttMessageBean;

    public MqttMessageEvent(int type, MqttMessageBean mqttMessageBean) {
        this.type = type;
        mMqttMessageBean = mqttMessageBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MqttMessageBean getMqttMessageBean() {
        return mMqttMessageBean;
    }

    public void setMqttMessageBean(MqttMessageBean mqttMessageBean) {
        mMqttMessageBean = mqttMessageBean;
    }
}
