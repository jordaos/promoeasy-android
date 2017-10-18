package com.jordao.promoeasy.contract;

import android.content.Context;

/**
 * Created by jordao on 08/10/17.
 */

public interface MainContract {
    interface View{
        void showError(String error);

        void goToHome();
        void goToWebAuthenticate();
        public Context getContext();
    }

    interface Presenter{
        /**
         * Se já estiver autenticado, chamar HomeActivity
         * Se não, chama para WebAuthenticateActivity
         */
        void authenticate();
        void isLoggedIn();
    }
}
