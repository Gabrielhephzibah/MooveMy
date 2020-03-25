package com.enyata.android.mvvm_java.data;

import com.enyata.android.mvvm_java.data.local.db.dao.DbHelper;
import com.enyata.android.mvvm_java.data.local.prefs.PreferencesHelper;
import com.enyata.android.mvvm_java.data.remote.ApiHelper;

import io.reactivex.Observable;

public interface DataManager extends  PreferencesHelper, ApiHelper {

    void setUserAsLoggedOut();

    void updateApiHeader(Long userId, String accessToken);

    void updateUserInfo(
            String accessToken,
            String firstname,
            LoggedInMode loggedInMode,
            String email);


    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0);
//        LOGGED_IN_MODE_GOOGLE(1),
//        LOGGED_IN_MODE_FB(2),
//        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
