package com.lsq.search.exception;

/**
 * @Classname MyFileNotFoundException
 * @Description TODO
 * @Date 2021/3/28 14:53
 * @Auth LSQ
 */
public class MyFileNotFoundException extends RuntimeException{
    public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
