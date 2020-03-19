package com.enyata.android.mvvm_java.data.remote;

public class ApiUtils {

        private ApiUtils() {}

        public static final String BASE_URL = "https://moove-backend.herokuapp.com/api/v1/";

        public static ApiService getAPIService() {

            return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
        }
}
