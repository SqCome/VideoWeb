package com.lsq.search.entity;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class User {
    private int id;
    private String username;
    private String password;
    private boolean vip;
    private String icon;
    private String email;
    private String targetLocation;
    private List<Video> videoList;


    public boolean isVip() {
        return vip;
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(String targetLocation) {
        this.targetLocation = targetLocation;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", vip=" + vip +
                ", icon='" + icon + '\'' +
                ", email='" + email + '\'' +
                ", targetLocation='" + targetLocation + '\'' +
                ", videoList=" + videoList +
                '}';
    }
}
