package com.jordao.promoeasy.contract;

import android.graphics.Bitmap;

import com.jordao.promoeasy.model.entity.User;

import java.util.ArrayList;

/**
 * Created by jordao on 22/10/17.
 */

public interface InstagramFunctionsContract {
    public void comentOnPost(String postId);
    public void likePost(String postId);
    public void followProfile(String profileId);
    public ArrayList<User> getFollowingList();
}
