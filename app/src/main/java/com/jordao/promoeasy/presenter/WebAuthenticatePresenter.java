package com.jordao.promoeasy.presenter;

import android.util.Log;
import android.widget.Toast;

import com.jordao.promoeasy.contract.WebAuthenticateContract;
import com.jordao.promoeasy.model.ApplicationData;
import com.jordao.promoeasy.model.InstagramApp;

/**
 * Created by jordao on 08/10/17.
 */

public class WebAuthenticatePresenter implements WebAuthenticateContract.Presenter {

    private WebAuthenticateContract.View mView;
    private InstagramApp instagramApp;

    public WebAuthenticatePresenter(WebAuthenticateContract.View view) {
        this.mView = view;

        instagramApp = new InstagramApp(view.getContext(), ApplicationData.CLIENT_ID, ApplicationData.CLIENT_SECRET, ApplicationData.CALLBACK_URL);
        instagramApp.setListener(new InstagramApp.OAuthAuthenticationListener() {
            @Override
            public void onSuccess() {
                mView.goToHome();
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mView.getContext(), error, Toast.LENGTH_SHORT).show();
                mView.goToLogin();
            }
        });
    }

    @Override
    public String getUrlAuth() {
        return instagramApp.getAuthUrl();
    }

    public boolean toCallBackUrl(String url){
        if (url.startsWith(InstagramApp.mCallbackUrl)) {
            String urls[] = url.split("=");
            instagramApp.getWebViewListner().onComplete(urls[1]);
            return true;
        }
        return false;
    }

    public interface OAuthWebAuthenticateListener {
        public abstract void onComplete(String accessToken);
        public abstract void onError(String error);
    }

}
