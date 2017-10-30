package com.jordao.promoeasy.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.jordao.promoeasy.model.entity.User;


/**
 * Created by jordao on 08/10/17.
 */

public class UserRepository {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private static final String SHARED = "instagram_preferences";
    private static final String API_USERNAME = "username";
    private static final String API_ID = "id";
    private static final String API_NAME = "name";
    private static final String API_ACCESS_TOKEN = "access_token";
    private static final String API_IS_BUSINESS = "is_business";

    private Context context;

    public UserRepository(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public void storeUser(User user) {
        editor.putString(API_ID, user.getId());
        editor.putString(API_NAME, user.getName());
        editor.putString(API_ACCESS_TOKEN, user.getAccess_token());
        editor.putString(API_USERNAME, user.getUserName());
        editor.putBoolean(API_IS_BUSINESS, user.isBusiness());
        editor.commit();
    }

    public void storeAccessToken(String accessToken) {
        editor.putString(API_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    /**
     * Get access token
     *
     * @return Access token
     */
    public String getAccessToken() {
        return sharedPref.getString(API_ACCESS_TOKEN, null);
    }

    /**
     * Reset access token and user name
     */
    public void resetUser() {
        editor.putString(API_ID, null);
        editor.putString(API_NAME, null);
        editor.putString(API_ACCESS_TOKEN, null);
        editor.putString(API_USERNAME, null);
        editor.putBoolean(API_IS_BUSINESS, false);
        editor.commit();
    }

    public User getUser(){
        Bitmap image = new ImageSaver(context).
                setFileName(sharedPref.getString(API_ID, null) + ".jpg").
                setDirectoryName("images").
                load();
        User user = new User(sharedPref.getString(API_ID, null), sharedPref.getString(API_NAME, null),
                sharedPref.getString(API_USERNAME, null), sharedPref.getString(API_ACCESS_TOKEN, null),
                image, sharedPref.getBoolean(API_IS_BUSINESS, false));
        return user;
    }

}
