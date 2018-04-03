package com.nadernabil.simpletwitterclient.Model.Objects;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class UserAccount {

    private Long id;
    private String user_name;
    private String user_handle;
    private String profile_image ; //URL
    private String backGround_Image ; //URL

    public UserAccount() {
    }

    public UserAccount(Long id, String user_name, String user_handle, String profile_image, String backGround_Image) {
        this.id = id;
        this.user_name = user_name;
        this.user_handle = user_handle;
        this.profile_image = profile_image;
        this.backGround_Image = backGround_Image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getBackGround_Image() {
        return backGround_Image;
    }

    public void setBackGround_Image(String backGround_Image) {
        this.backGround_Image = backGround_Image;
    }
}
