package com.lsq.search.utils;

/**
 * @Classname ResCode
 * @Description TODO
 * @Date 2021/3/5 15:51
 * @Auth LSQ
 */
public enum ResCode {
    SUCCESS(0, "请求成功"),
    WARN(1,"请求不通过"),
    ERROR(-1, "服务异常");

    private final int code;
    private final String msg;

    ResCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
