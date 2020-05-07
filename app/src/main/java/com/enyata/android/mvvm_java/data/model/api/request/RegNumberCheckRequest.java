package com.enyata.android.mvvm_java.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegNumberCheckRequest {


    private RegNumberCheckRequest() {
        //don't instantiate
    }
    public  static  class Request{


        @Expose
        @SerializedName("registration_number")
        private String registrationNumber;

        public Request(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }




        public String getRegistrationNumber() {
            return registrationNumber;
        }



        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            RegNumberCheckRequest.Request request = (RegNumberCheckRequest.Request) obj;



            return registrationNumber != null ? !registrationNumber.equals(request.registrationNumber) : request.registrationNumber != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);

            return result;
        }

    }

}

