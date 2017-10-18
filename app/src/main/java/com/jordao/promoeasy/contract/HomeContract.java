package com.jordao.promoeasy.contract;

import android.content.Context;

/**
 * Created by jordao on 14/10/17.
 */

public interface HomeContract {
    interface View{
        public Context getContext();
    }

    interface Presenter{
        public void logout();
    }
}
