package com.enyata.android.mvvm_java.data.model.api.myData;

import android.annotation.TargetApi;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VehicleCollection {

    private VehicleCollection() {
        //don not instantiate
    }

    public static class Request {

        @Expose
        @SerializedName("part")
        private String part;

        @Expose
        @SerializedName("image_url")
        private String[] imageUrl;

        @Expose
        @SerializedName("remark")
        private String remark;

        public Request(String part, String[] imageUrl, String remark) {
            this.part = part;
            this.imageUrl = imageUrl;
            this.remark = remark;
        }

        public String getPart() {
            return part;
        }

        public String[] getImageUrl() {
            return imageUrl;
        }

        public String getRemark() {
            return remark;
        }

        public void setPart(String part) {
            this.part = part;
        }

        public void setImageUrl(String[] imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VehicleCollection.Request request = (VehicleCollection.Request) o;
            return Objects.equals(remark, request.remark) &&
                    Arrays.equals(imageUrl, request.imageUrl) &&
                    Objects.equals(part, request.part);
        }


        @Override
        public int hashCode() {
            int result = Objects.hash(remark, part);
            result = 31 * result + Arrays.hashCode(imageUrl);
            return result;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "part='" + part + '\'' +
                    ", imageUrl=" + Arrays.toString(imageUrl) +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }


//        @Override
//        public boolean equals(Object object) {
//            if (this == object) return true;
//            if (object == null || getClass() != object.getClass()) return false;
//            Request request = (Request) object;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                return Objects.equals(getPart(), request.getPart()) &&
//                        Arrays.equals(getImageUrl(), request.getImageUrl()) &&
//                        Objects.equals(getRemark(), request.getRemark());
//            }
//            else {
//                return false;
//            }
//        }
//
//
//        @Override
//        public int hashCode() {
//            int result = 0;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                result = Objects.hash(getPart(), getRemark());
//            }
//            result = 31 * result + Arrays.hashCode(getImageUrl());
//            return result;
//        }


    //        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) {
//                return true;
//            }
//
//            if (obj == null || getClass() != obj.getClass()) {
//                return false;
//            }
//
//          VehicleCollection.Request request = (Request)obj;
//
//            if (part != null ? !part.equals(request.part) : request.part != null) {
//                return false;
//            }if (imageUrl != null ? !imageUrl.equals(request.imageUrl) : request.imageUrl != null) {
//                return false;
//            }
//            return remark != null ? !remark.equals(request.remark) : request.remark != null;
//
//        }


//        @Override
//        public int hashCode() {
//            int result = 0;
//            result = 31 * result + (part != null ? part.hashCode() : 0);
//            result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
//            result = 31 * result + (remark != null ? remark.hashCode() : 0);
//            return result;
//





    }

