package com.enyata.android.mvvm_java.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Alert {

    public static void showSuccess(Context context, String  message){
        FancyToast.makeText(context,message,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
    }

    public static void showFailed(Context context,String  message){
        FancyToast.makeText(context,message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
    }

    public static void showWarning(Context context,String  message){
        FancyToast.makeText(context,message,FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
    }

    public static void showInfo(Context context,String  message){
        FancyToast.makeText(context,message,FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
    }

    public static void showPositivePopup(Context context, String title,String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void showPositiveAndNegativePopup(Context context, String title,String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
