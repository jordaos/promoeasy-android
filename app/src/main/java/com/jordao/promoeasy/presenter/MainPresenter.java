package com.jordao.promoeasy.presenter;

import android.util.Log;
import android.widget.Toast;

import com.jordao.promoeasy.contract.MainContract;
import com.jordao.promoeasy.model.ApplicationData;
import com.jordao.promoeasy.model.InstagramApp;

/**
 * Created by jordao on 08/10/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private InstagramApp instagramApp;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        instagramApp = new InstagramApp(view.getContext(), ApplicationData.CLIENT_ID, ApplicationData.CLIENT_SECRET, ApplicationData.CALLBACK_URL);
    }

    @Override
    public void authenticate() {
        if(instagramApp.getUser().getAccess_token() == null)
            view.goToWebAuthenticate();
        else
            view.goToHome();
    }

    @Override
    public void isLoggedIn() {
        if(instagramApp.getUser().getAccess_token() != null)
            view.goToHome();
    }
}
