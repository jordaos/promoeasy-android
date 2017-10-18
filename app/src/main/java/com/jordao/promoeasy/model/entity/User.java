package com.jordao.promoeasy.model.entity;

/**
 * Created by jordao on 08/10/17.
 */

public class User {
    private String id;
    private String name;
    private String userName;
    private String access_token;

    public User(String id, String name, String userName, String access_token) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.access_token = access_token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
