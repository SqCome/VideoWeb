package com.lsq.search.serviceimpl;

import com.lsq.search.entity.ResEntity;
import com.lsq.search.entity.User;
import com.lsq.search.entity.Video;
import com.lsq.search.mapper.UserMapper;
import com.lsq.search.mapper.VideoMapper;
import com.lsq.search.service.FileStorageService;
import com.lsq.search.service.VideoService;
import com.lsq.search.utils.ResCode;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserMapper userMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private User userUtil;


    @Override
    public String getVideoUrl(int id) {
        return videoMapper.selectVideoById(id).getUrl();
    }

    /**
     * 上传视频
     * @param video
     * @param file
     * @param user
     * @return
     */
    @Override
    public ResEntity uploadVideo(Video video, MultipartFile file,User user) {
        ResEntity resEntity = new ResEntity();
        try {
            int userId = user.getId();
            String videoUrl = fileStorageService.storeFile(file,user,"Video");
            video.setUrl(videoUrl);
            video.setUserId(userId);
            videoMapper.insertVideo(video);
            resEntity.setCode(ResCode.SUCCESS.getCode());
            resEntity.setMessage(ResCode.SUCCESS.getMsg());
            resEntity.setData("上传成功！");
        }catch (Exception e){
            resEntity.setCode(ResCode.ERROR.getCode());
            resEntity.setMessage(ResCode.ERROR.getMsg());
            resEntity.setData("上传失败！"+e);
        }
        return resEntity;
    }

    /**
     * 评论功能
     * @param video
     * @param user
     * @param comment
     * @return
     */
    @Override
    public ResEntity setComment(Video video, User user, String comment) {
        return null;
    }

    /**
     * 点赞功能
     * @param video
     * @param user
     * @return
     */
    @Override
    public ResEntity likeUpdate(Video video, User user) {
        return null;
    }


}
