package com.jordao.promoeasy.model.entity;

import android.graphics.Bitmap;

/**
 * Created by jordao on 08/10/17.
 */

public class User {
    private String id;
    private String name;
    private String userName;
    private String access_token;
    private Bitmap profileImage;
    private boolean is_business;

    public User(String id, String name, String userName, String access_token) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.access_token = access_token;

    }

    public User(String id, String name, String userName, String access_token, Bitmap profileImage, boolean is_business) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.access_token = access_token;
        this.profileImage = profileImage;
        this.is_business = is_business;
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

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Bitmap profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isBusiness() {
        return is_business;
    }

    public void setIsBusiness(boolean is_business) {
        this.is_business = is_business;
    }
}
