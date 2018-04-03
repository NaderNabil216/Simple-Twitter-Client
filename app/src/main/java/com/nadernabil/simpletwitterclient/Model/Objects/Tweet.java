package com.nadernabil.simpletwitterclient.Model.Objects;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class Tweet {

    private int id;
    private String Content ;
    private String follower_id ; //URL

    public Tweet(int id, String content, String follower_id) {
        this.id = id;
        Content = content;
        this.follower_id = follower_id;
    }

    public Tweet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(String follower_id) {
        this.follower_id = follower_id;
    }
}
