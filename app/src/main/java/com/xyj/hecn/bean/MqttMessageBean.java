package com.xyj.hecn.bean;

import java.util.List;

/**
 * null.java
 * description: TODO
 *
 * @author : Licy
 * @date : 2021/4/26
 * email ：licy3051@qq.com
 */
public class MqttMessageBean {

    private String department;
    private List<PatientBean> data;

    public MqttMessageBean(String department, List<PatientBean> data) {
        this.department = department;
        this.data = data;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<PatientBean> getData() {
        return data;
    }

    public void setData(List<PatientBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MqttMessageBean{" +
                "department='" + department + '\'' +
                ", data=" + data +
                '}';
    }
}
