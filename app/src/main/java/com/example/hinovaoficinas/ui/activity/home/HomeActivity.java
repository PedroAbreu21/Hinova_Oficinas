package com.example.hinovaoficinas.ui.activity.home;

import static com.example.hinovaoficinas.utils.Constants.EXTRA_USER_ID;
import static com.example.hinovaoficinas.utils.Constants.EXTRA_USER_TYPE;
import static com.example.hinovaoficinas.utils.Constants.TAG_ERROR_FIRESTORE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hinovaoficinas.R;
import com.example.hinovaoficinas.databinding.ActivityHomeBinding;
import com.example.hinovaoficinas.network.firestore.FirestoreService;
import com.example.hinovaoficinas.network.firestore.FirestoreServiceImpl;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreUserNameCallback;
import com.example.hinovaoficinas.ui.activity.indication.IndicationActivity;
import com.example.hinovaoficinas.ui.activity.register.FormRegisterActivity;
import com.example.hinovaoficinas.ui.activity.workshop.WorkShopActivity;

public class HomeActivity extends AppCompatActivity implements FirestoreUserNameCallback {

    private ActivityHomeBinding binding;

    private FirestoreService firestoreService;

    private Long userType;
    private String userID;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getUserInformationFromIntent();
        initializeDependencies();
        firestoreService.getUserNameFromDB(userID, this);

        binding.btnWorkshop.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, WorkShopActivity.class);
            startActivity(intent);
        });

    }

    private void getUserInformationFromIntent() {
        Intent intent = getIntent();
        userType = intent.getLongExtra(EXTRA_USER_TYPE, 501);
        userID = intent.getStringExtra(EXTRA_USER_ID);
    }

    private void initializeDependencies() {
        firestoreService = new FirestoreServiceImpl(getApplicationContext());
    }

    @Override
    public void onSuccessGetUserName(String userName) {
        this.userName = userName;
        verifyUserType();
    }

    @Override
    public void onErrorGetUserName(Exception e) {
        Log.e(TAG_ERROR_FIRESTORE, e.toString());
        Toast.makeText(this, getString(R.string.toast_generic_error), Toast.LENGTH_SHORT).show();
    }

    private void verifyUserType() {
        if (userType == 0) {
            setConsultantScreen();
        } else {
            setAssociateScreen();
        }
    }

    private void setConsultantScreen() {
        binding.txtTittle.setText(getString(R.string.txt_hello_consultant, userName));
        binding.imgRegister.setVisibility(View.VISIBLE);
        binding.txtRegister.setVisibility(View.VISIBLE);

        binding.btnSecundary.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FormRegisterActivity.class);
            startActivity(intent);
        });

    }

    private void setAssociateScreen() {
        binding.txtTittle.setText(getString(R.string.txt_hello_associate, userName));
        binding.imgFriendIndication.setVisibility(View.VISIBLE);
        binding.txtFriendIndication.setVisibility(View.VISIBLE);

        binding.btnSecundary.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, IndicationActivity.class);
            intent.putExtra(EXTRA_USER_ID, userID);
            intent.putExtra(EXTRA_USER_TYPE, userType);
            startActivity(intent);
        });

    }

}