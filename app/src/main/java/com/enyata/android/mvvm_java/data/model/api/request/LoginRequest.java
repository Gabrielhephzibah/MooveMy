package com.enyata.android.mvvm_java.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    private LoginRequest(){
        //don't instantiate
    }

    public  static  class Request{


        @Expose
        @SerializedName("email")
        private String email;

        @Expose
        @SerializedName("password")
        private String password;

        public  Request(String email, String password){
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            LoginRequest.Request request = (LoginRequest.Request) obj;

            if (email != null ? !email.equals(request.email) : request.email != null) {
                return false;
            }

            return password != null ? !password.equals(request.password) : request.password != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (email != null ? email.hashCode() : 0);
            result = 31 * result + (password != null ? password.hashCode() : 0);
            return result;
        }

    }



}
