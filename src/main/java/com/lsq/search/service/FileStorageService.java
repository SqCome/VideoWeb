package com.lsq.search.service;

import com.lsq.search.entity.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname FileStorageService
 * @Description TODO
 * @Date 2021/3/28 12:39
 * @Auth LSQ
 */
public interface FileStorageService {

    public String storeFile(MultipartFile file,User user,String fileType);
    public Resource loadFileAsResource(String fileName,User user,String fileType);
}
