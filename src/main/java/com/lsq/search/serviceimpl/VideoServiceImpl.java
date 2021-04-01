package com.lsq.search.serviceimpl;

import com.lsq.search.entity.ResEntity;
import com.lsq.search.entity.User;
import com.lsq.search.entity.Video;
import com.lsq.search.mapper.VideoMapper;
import com.lsq.search.service.FileStorageService;
import com.lsq.search.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname VideoServiceImpl
 * @Description TODO
 * @Date 2021/3/31 18:13
 * @Auth LSQ
 */

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private Video videoUtil;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    FileStorageService fileStorageService;


    @Override
    public ResEntity uploadVideo(Video video, MultipartFile file) {
        return null;
    }

    @Override
    public Resource downloadVideo(User user,String fileName) {
        Resource resource = fileStorageService.loadFileAsResource(fileName,user,"Icon");
        return resource;
    }
}
