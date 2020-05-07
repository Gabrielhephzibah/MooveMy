package com.enyata.android.mvvm_java.data;

import com.enyata.android.mvvm_java.data.local.db.dao.DbHelper;
import com.enyata.android.mvvm_java.data.local.prefs.PreferencesHelper;
import com.enyata.android.mvvm_java.data.remote.ApiHelper;

import io.reactivex.Observable;

public interface DataManager extends  PreferencesHelper, ApiHelper {

    void setUserAsLoggedOut();

    void updateApiHeader(Long userId, String accessToken);
    void updateLoginStatus(
            LoggedInMode loggedInMode);

    void updateUserInfo(
            String accessToken,
            String firstname,
            String email);


    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_LOGGED_IN(1);


        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
