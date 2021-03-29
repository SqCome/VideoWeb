package com.lsq.search.exception;

/**
 * @Classname FileStorageException
 * @Description TODO
 * @Date 2021/3/28 14:52
 * @Auth LSQ
 */
public class FileStorageException extends RuntimeException{
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
