package com.example.hinovaoficinas.ui.activity.selectUser;

import static com.example.hinovaoficinas.utils.Constants.EXTRA_TYPE_LOGIN;
import static com.example.hinovaoficinas.utils.Constants.EXTRA_USER_ID;
import static com.example.hinovaoficinas.utils.Constants.EXTRA_USER_TYPE;
import static com.example.hinovaoficinas.utils.Constants.TAG_ERROR_FIRESTORE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hinovaoficinas.R;
import com.example.hinovaoficinas.databinding.ActivitySelectUserBinding;
import com.example.hinovaoficinas.network.authentication.callback.AuthenticationIsLoggedCallback;
import com.example.hinovaoficinas.network.authentication.AuthenticationService;
import com.example.hinovaoficinas.network.authentication.AuthenticationServiceImpl;
import com.example.hinovaoficinas.network.firestore.FirestoreService;
import com.example.hinovaoficinas.network.firestore.FirestoreServiceImpl;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreTypeUserCallback;
import com.example.hinovaoficinas.ui.activity.home.HomeActivity;
import com.example.hinovaoficinas.ui.activity.login.FormLoginActivity;
import com.example.hinovaoficinas.utils.alertDialog.AlertDialogCallback;
import com.example.hinovaoficinas.utils.alertDialog.AlertDialogDisplay;
import com.example.hinovaoficinas.utils.alertDialog.AlertDialogImpl;

public class SelectUserActivity extends AppCompatActivity implements FirestoreTypeUserCallback, AuthenticationIsLoggedCallback, AlertDialogCallback {

    private ActivitySelectUserBinding binding;

    private FirestoreService firestoreService;
    private AuthenticationService authenticationService;
    private AlertDialogDisplay alertDialogDisplay;

    private long userType;
    private String userID;
    private int typeLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeDependencies();

        binding.btnAssociate.setOnClickListener(v -> {
            typeLogin = 1;
            authenticationService.checkUserLogged(this);
        });

        binding.btnConsultant.setOnClickListener(v -> {
            typeLogin = 0;
            authenticationService.checkUserLogged(this);
        });
    }

    private void initializeDependencies() {
        authenticationService = new AuthenticationServiceImpl(getApplicationContext());
        firestoreService = new FirestoreServiceImpl(getApplicationContext());
        alertDialogDisplay = new AlertDialogImpl(this);
    }

    @Override
    public void onSuccessIsLogged(String userID) {
        this.userID = userID;
        firestoreService.getUserTypeFromDB(userID, this);
    }

    @Override
    public void onErrorIsLogged() {
        startFormLoginActivity();
    }

    @Override
    public void onSuccessGetType(Long userType) {
        this.userType = userType;
        verifyUserTypeAndLogin();
    }

    @Override
    public void onErrorGetType(Exception e) {
        Log.e(TAG_ERROR_FIRESTORE, e.toString());
        Toast.makeText(this, getString(R.string.toast_generic_error), Toast.LENGTH_SHORT).show();
    }

    private void verifyUserTypeAndLogin() {
        if (userType == typeLogin) {
            startHomeActivity();
        } else {
            configAlertDialog();
        }
    }

    private void configAlertDialog() {
        String[] userTypes = getResources().getStringArray(R.array.userTypes);
        int userNoType = (userType == 0) ? 1 : 0;

        String alertTittle = getResources().getString(R.string.alert_tittle);
        String alertMessage = getResources().getString(R.string.alert_message_select_activity, userTypes[(int) userType], userTypes[userNoType]);
        String alertPositive = getResources().getString(R.string.alert_positive);
        String alertNegative = getResources().getString(R.string.alert_negative);

        alertDialogDisplay.showAlertDialog(alertTittle, alertMessage, alertPositive, alertNegative, this);
    }

    @Override
    public void onPositive() {
        authenticationService.signOutFirebase();
        startFormLoginActivity();
    }

    @Override
    public void onNegative() {
        startHomeActivity();
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(EXTRA_USER_TYPE, userType);
        intent.putExtra(EXTRA_USER_ID, userID);
        startActivity(intent);
    }

    private void startFormLoginActivity() {
        Intent intent = new Intent(this, FormLoginActivity.class);
        intent.putExtra(EXTRA_TYPE_LOGIN, typeLogin);
        startActivity(intent);
    }

}