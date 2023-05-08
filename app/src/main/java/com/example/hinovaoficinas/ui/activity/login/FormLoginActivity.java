package com.example.hinovaoficinas.ui.activity.login;

import static com.example.hinovaoficinas.utils.Constants.EXTRA_TYPE_LOGIN;
import static com.example.hinovaoficinas.utils.Constants.EXTRA_USER_ID;
import static com.example.hinovaoficinas.utils.Constants.EXTRA_USER_TYPE;
import static com.example.hinovaoficinas.utils.Constants.TAG_ERROR_FIRESTORE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hinovaoficinas.ui.activity.home.HomeActivity;
import com.example.hinovaoficinas.R;
import com.example.hinovaoficinas.databinding.ActivityFormLoginBinding;
import com.example.hinovaoficinas.network.authentication.callback.AuthenticationSignInCallback;
import com.example.hinovaoficinas.network.authentication.AuthenticationService;
import com.example.hinovaoficinas.network.authentication.AuthenticationServiceImpl;
import com.example.hinovaoficinas.network.firestore.FirestoreService;
import com.example.hinovaoficinas.network.firestore.FirestoreServiceImpl;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreTypeUserCallback;
import com.example.hinovaoficinas.utils.alertDialog.AlertDialogCallback;
import com.example.hinovaoficinas.utils.alertDialog.AlertDialogDisplay;
import com.example.hinovaoficinas.utils.alertDialog.AlertDialogImpl;
import com.example.hinovaoficinas.utils.error.ErrorDisplayImpl;
import com.example.hinovaoficinas.utils.error.ErrorDisplay;

public class FormLoginActivity extends AppCompatActivity implements FirestoreTypeUserCallback, AuthenticationSignInCallback, AlertDialogCallback {

    private ErrorDisplay errorDisplay;
    private ActivityFormLoginBinding binding;
    private FirestoreService firestoreService;
    private AuthenticationService authenticationService;
    private AlertDialogDisplay alertDialogDisplay;
    private String userID;
    private int typeLogin;
    private long userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getTypeLoginFromIntent();
        initializeDependencies();
        setTittle();
        setErrorDisplay();

        binding.btnLogin.setOnClickListener(v -> loginValidation());

    }

    private void initializeDependencies() {
        authenticationService = new AuthenticationServiceImpl(getApplicationContext());
        firestoreService = new FirestoreServiceImpl(getApplicationContext());
        alertDialogDisplay = new AlertDialogImpl(this);
    }

    private void getTypeLoginFromIntent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TYPE_LOGIN)) {
            typeLogin = intent.getIntExtra(EXTRA_TYPE_LOGIN, 501);
        }
    }

    private void setTittle() {
        String[] userTypes = getResources().getStringArray(R.array.userTypes);
        String tittleLogin = getResources().getString(R.string.txt_tittle_login, userTypes[typeLogin]);
        binding.txtTittle.setText(tittleLogin);
    }

    private void setErrorDisplay() {
        errorDisplay = new ErrorDisplayImpl(
                null,
                binding.edtEmail,
                null,
                null,
                null,
                binding.edtPassword
        );
    }

    private void loginValidation() {
        String email = binding.edtEmail.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        String empty = getString(R.string.error_display_empty);

        if (email.isEmpty()) {
            errorDisplay.showEmailError(empty);
        } else if (password.isEmpty()) {
            errorDisplay.showPasswordError(empty);
        } else {
            authenticationService.signInFirebase(email, password, this);
        }

    }

    @Override
    public void onSuccessSingIn(String userID) {
        this.userID = userID;
        firestoreService.getUserTypeFromDB(userID, this);
    }

    @Override
    public void onErrorSignIn(int error) {
        String[] errors = getResources().getStringArray(R.array.errorArraySignIn);
        Log.e(TAG_ERROR_FIRESTORE, errors[error]);
        Toast.makeText(this, errors[error], Toast.LENGTH_SHORT).show();
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
        String alertMessage = getResources().getString(R.string.alert_message_login_activity, userTypes[(int) userType], userTypes[userNoType]);
        String alertPositive = getResources().getString(R.string.alert_positive);
        String alertNegative = getResources().getString(R.string.alert_negative);

        alertDialogDisplay.showAlertDialog(alertTittle, alertMessage, alertPositive, alertNegative, this);
    }

    @Override
    public void onPositive() {
        startHomeActivity();
    }

    @Override
    public void onNegative() {
        authenticationService.signOutFirebase();
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(EXTRA_USER_TYPE, userType);
        intent.putExtra(EXTRA_USER_ID, userID);
        startActivity(intent);
        finish();
    }

}