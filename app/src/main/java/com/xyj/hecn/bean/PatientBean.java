package com.xyj.hecn.bean;

/**
 * null.java
 * description: TODO
 *
 * @author : Licy
 * @date : 2021/4/25
 * email ：licy3051@qq.com
 */
public class PatientBean {

    private String name;
    private String number;
    private int called;

    public PatientBean(String name, String number, int called) {
        this.name = name;
        this.number = number;
        this.called = called;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCalled() {
        return called;
    }

    public void setCalled(int called) {
        this.called = called;
    }
}
