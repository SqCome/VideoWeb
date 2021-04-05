package com.lsq.search.mapper;

import com.lsq.search.entity.User;
import com.lsq.search.entity.Video;
import org.springframework.stereotype.Repository;

/**
 * @Classname VideoMapper
 * @Description TODO
 * @Date 2021/3/31 19:08
 * @Auth LSQ
 */


@Repository
public interface VideoMapper {
    Video selectVideoById(int id);
    int insertVideo(Video video);
    int updateVideo(Video video);
    User getMyVideos(User user);
}
