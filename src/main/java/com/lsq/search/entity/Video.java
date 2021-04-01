package com.lsq.search.entity;


import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;

@Repository
public class Video {
    private int id;
    private int userId;
    private String url;
    private String title;
    private int commentNum;
    private int likedNum;
    private Data updateTime;
    private boolean reviewStatus;
    private boolean showStatus;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(int likedNum) {
        this.likedNum = likedNum;
    }

    public Data getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Data updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(boolean reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public boolean isShowStatus() {
        return showStatus;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", userId=" + userId +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", commentNum=" + commentNum +
                ", likedNum=" + likedNum +
                ", updateTime=" + updateTime +
                ", reviewStatus=" + reviewStatus +
                ", showStatus=" + showStatus +
                '}';
    }
}
