package com.lsq.search.entity;

import com.lsq.search.utils.ResCode;
import org.springframework.stereotype.Repository;

/**
 * @Classname ResEntity
 * @Description 返回报文格式
 * @Date 2021/3/5 15:32
 * @Auth LSQ
 */

@Repository
public class ResEntity {
    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
