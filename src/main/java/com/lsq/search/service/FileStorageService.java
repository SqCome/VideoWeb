package com.lsq.search.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname FileStorageService
 * @Description TODO
 * @Date 2021/3/28 12:39
 * @Auth LSQ
 */
public interface FileStorageService {

    public String storeFile(MultipartFile file);
    public Resource loadFileAsResource(String fileName);
}
