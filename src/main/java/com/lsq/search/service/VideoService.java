package com.lsq.search.service;

import com.lsq.search.entity.ResEntity;
import com.lsq.search.entity.User;
import com.lsq.search.entity.Video;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname VideoService
 * @Description TODO
 * @Date 2021/3/31 18:12
 * @Auth LSQ
 */

public interface VideoService {
    public String getVideoUrl(int id);
    public ResEntity uploadVideo(Video video, MultipartFile file, User user);
    public ResEntity setComment(Video video,User user,String comment);
    public ResEntity likeUpdate(Video video,User user);
}
