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

    private int result;
    private String message;
    private List<DataBean> data;

    public MqttMessageBean(int result, String message, List<DataBean> data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String recordId;
        private String name;
        private String idcard;
        private String sex;
        private String mobile;
        private String deptCode;
        private String deptName;
        private String roomCode;
        private String roomName;
        /**
         * 1是当前体检中
         * 0是未在当前体检中
         */
        private String currentVisit;

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getDeptCode() {
            return deptCode;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getRoomCode() {
            return roomCode;
        }

        public void setRoomCode(String roomCode) {
            this.roomCode = roomCode;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getCurrentVisit() {
            return currentVisit;
        }

        public void setCurrentVisit(String currentVisit) {
            this.currentVisit = currentVisit;
        }
    }
}
