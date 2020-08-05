package com.enyata.android.mvvm_java.data.remote;

public class ApiUtils {

        private ApiUtils() {}

        public static final String BASE_URL = "https://api.moove.africa/api/v1/";

        public static ApiService getAPIService() {

            return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
        }
}
