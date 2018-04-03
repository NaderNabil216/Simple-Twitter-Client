package com.nadernabil.simpletwitterclient.Model.Objects;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class Follower {

    private int id;
    private String user_name;
    private String bio ="";
    private String profile_image ; //URL
    private String backGround_Image ; //URL
    private int follows ; // user account id that this follower follows // foreign key

    public Follower(int id, String user_name, String bio, String profile_image, String backGround_Image, int follows) {
        this.id = id;
        this.user_name = user_name;
        this.bio = bio;
        this.profile_image = profile_image;
        this.backGround_Image = backGround_Image;
        this.follows = follows;
    }

    public Follower(int id, String user_name, String profile_image, String backGround_Image, int follows) {
        this.id = id;
        this.user_name = user_name;
        this.profile_image = profile_image;
        this.backGround_Image = backGround_Image;
        this.follows = follows;
    }

    public Follower() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }
}
