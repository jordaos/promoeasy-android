package com.jordao.promoeasy.contract;

import android.content.Context;

/**
 * Created by jordao on 08/10/17.
 */

public interface WebAuthenticateContract {
    interface View{
        public Context getContext();
        public void goToHome();
        public void goToLogin();
    }

    interface Presenter{
        public String getUrlAuth();

        public boolean toCallBackUrl(String url);
    }
}
