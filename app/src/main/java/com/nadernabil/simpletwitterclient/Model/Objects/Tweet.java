package com.nadernabil.simpletwitterclient.Model.Objects;

/**
 * Created by NaderNabil@gmail.com on 4/2/2018.
 */

public class Tweet {

    private Long id;
    private String Content ;
    private Long follower_id ; //URL
    private String Created_at; // " d MMM yy , hh:mm aaa "

    public Tweet() {
    }

    public Tweet(Long id, String content, Long follower_id, String created_at) {
        this.id = id;
        Content = content;
        this.follower_id = follower_id;
        Created_at = created_at;
    }

    public String getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(String created_at) {
        Created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Long getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(Long follower_id) {
        this.follower_id = follower_id;
    }
}
