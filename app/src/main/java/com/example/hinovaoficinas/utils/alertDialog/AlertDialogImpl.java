package com.example.hinovaoficinas.utils.alertDialog;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogImpl implements AlertDialogDisplay {
    private final Context context;

    public AlertDialogImpl(Context context) {
        this.context = context;
    }

    @Override
    public void showAlertDialog(String alertTittle, String alertMessage, String alertPositive, String alertNegative, AlertDialogCallback callback) {
        AlertDialog.Builder exitLogin = new AlertDialog.Builder(context);

        exitLogin.setTitle(alertTittle);
        exitLogin.setMessage(alertMessage);

        exitLogin.setPositiveButton(alertPositive, (dialog, which) -> callback.onPositive());
        exitLogin.setNegativeButton(alertNegative, (dialog, which) -> callback.onNegative());

        exitLogin.create().show();
    }
}
