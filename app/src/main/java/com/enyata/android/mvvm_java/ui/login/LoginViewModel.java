/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.enyata.android.mvvm_java.ui.login;

import android.text.TextUtils;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.request.LoginRequest;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.CommonUtils;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;



public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isEmailAndPasswordValid(String email, String password) {
        return !TextUtils.isEmpty(email) && CommonUtils.isEmailValid(email) && !TextUtils.isEmpty(password);
    }

    public boolean isLengthEqualsToSeven(String password) {
        return password.length() >= 7;
    }


    public void  onLoginInspector(LoginRequest.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
               .loginInspector(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onResponse(response);
                    String token = response.getToken();
                    String userEmail = response.getData().getEmail();
                    String firstname = response.getData().getFirstName();
                    getDataManager().updateUserInfo(token,firstname,userEmail);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void onLogin(){
        getNavigator().login();

    }

    public void setCurrentUserName(String name){
        getDataManager().setCurrentUserName(name);
    }

    public void onDispose(){
        onCleared();
    }



    public void onServerLoginClick() {
        getNavigator().login();
    }
}
