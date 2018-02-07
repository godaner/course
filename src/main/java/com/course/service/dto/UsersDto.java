package com.course.service.dto;

import org.springframework.web.multipart.MultipartFile;

public class UsersDto {
    private Long userId;
    private Byte sex;
    private Integer birthday;
    private String imgUrl;
    private String description;
    private String username;
    private String password;
    private String rePassword;
    private MultipartFile headFile;

    public MultipartFile getHeadFile() {
        return headFile;
    }

    public void setHeadFile(MultipartFile headFile) {
        this.headFile = headFile;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "UsersDto{" +
                "userId=" + userId +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
