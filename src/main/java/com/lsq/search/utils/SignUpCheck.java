package com.lsq.search.utils;

/**
 * 用户检查
 */
public enum SignUpCheck {
    /**
     *
     */
    ERROR(-1,"服务器异常"),SUCCESS(0,"操作成功"),NAME_REPEAT(1,"用户名已存在"),
    NAME_NOT_EXIT(2,"用户名不存在"),PASSWORD_ERROR(3,"密码错误");

    private final int infoIntValue;
    private final String infoChineseValue;

    SignUpCheck(int infoIntValue, String infoChineseValue) {
        this.infoIntValue = infoIntValue;
        this.infoChineseValue = infoChineseValue;
    }

    public int getInfoIntValue() {
        return infoIntValue;
    }

    public String getInfoChineseValue() {
        return infoChineseValue;
    }

    @Override
    public String toString() {
        return "SignUpCheck{" +
                "infoIntValue=" + infoIntValue +
                ", infoChineseValue='" + infoChineseValue + '\'' +
                '}';
    }
}
