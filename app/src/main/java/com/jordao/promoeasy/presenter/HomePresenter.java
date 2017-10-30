package com.jordao.promoeasy.presenter;

import com.jordao.promoeasy.contract.HomeContract;
import com.jordao.promoeasy.model.instagram.ApplicationData;
import com.jordao.promoeasy.model.InstagramApp;
import com.jordao.promoeasy.model.entity.User;

/**
 * Created by jordao on 14/10/17.
 */

public class HomePresenter implements HomeContract.Presenter{
    private HomeContract.View view;
    private InstagramApp instagramApp;
    private User user;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        instagramApp = new InstagramApp(view.getContext(), ApplicationData.CLIENT_ID, ApplicationData.CLIENT_SECRET, ApplicationData.CALLBACK_URL);
        this.user = instagramApp.getUser();
    }


    @Override
    public User getUser() {
        return instagramApp.getUser();
    }

    @Override
    public void logout() {
        instagramApp.resetUser();
    }
}
