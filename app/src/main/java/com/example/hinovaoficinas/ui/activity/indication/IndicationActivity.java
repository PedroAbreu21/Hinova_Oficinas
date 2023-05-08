package com.example.hinovaoficinas.ui.activity.indication;

import static com.example.hinovaoficinas.utils.Constants.DB_USER_ASSOCIATION;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_COLLECTION;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_CPF;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_NAME;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_PHONE;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_PLATE;
import static com.example.hinovaoficinas.utils.Constants.EXTRA_USER_ID;
import static com.example.hinovaoficinas.utils.Constants.EXTRA_USER_TYPE;
import static com.example.hinovaoficinas.utils.Constants.POST_INDICATION_COPY;
import static com.example.hinovaoficinas.utils.Constants.POST_INDICATION_SENDER;
import static com.example.hinovaoficinas.utils.DateManager.getCurrentDate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hinovaoficinas.R;
import com.example.hinovaoficinas.databinding.ActivityIndicationBinding;
import com.example.hinovaoficinas.data.models.indication.Indicacao;
import com.example.hinovaoficinas.data.models.indication.IndicacaoEnvio;
import com.example.hinovaoficinas.network.firestore.FirestoreService;
import com.example.hinovaoficinas.network.firestore.FirestoreServiceImpl;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreUserInformationCallback;
import com.example.hinovaoficinas.network.workshopApi.callback.ApiIndicationCallback;
import com.example.hinovaoficinas.ui.activity.home.HomeActivity;
import com.example.hinovaoficinas.utils.error.ErrorDisplayImpl;
import com.example.hinovaoficinas.utils.error.ErrorDisplay;
import com.example.hinovaoficinas.network.workshopApi.ApiService;
import com.example.hinovaoficinas.network.workshopApi.ApiServiceIonImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Objects;

public class IndicationActivity extends AppCompatActivity implements ApiIndicationCallback, FirestoreUserInformationCallback {

    private ActivityIndicationBinding binding;
    private FirestoreService firestoreService;


    private ErrorDisplay errorDisplay;

    private String userID;
    private Long userType;
    private String[] userData = new String[6];
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIndicationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getUserInformationFromIntent();
        initializeDependencies();
        firestoreService.getUserInformationFromDB(userID, this);

        binding.btnIndication.setOnClickListener(v -> indicationValidation());

    }

    private void getUserInformationFromIntent() {
        Intent intent = getIntent();
        userID = intent.getStringExtra(EXTRA_USER_ID);
        userType = intent.getLongExtra(EXTRA_USER_TYPE, 501);
    }

    private void initializeDependencies() {
        apiService = new ApiServiceIonImpl(getApplicationContext());
        firestoreService = new FirestoreServiceImpl(getApplicationContext());
    }

    @Override
    public void onSuccessGetInformation(String[] userData) {
        this.userData = userData;
        setErrorDisplay();
    }

    @Override
    public void onErrorGetInformation(Exception e) {

    }

    private void setErrorDisplay() {
        errorDisplay = new ErrorDisplayImpl(
                binding.edtName,
                binding.edtEmail,
                binding.edtPhone
        );
    }

    private void indicationValidation() {
        String name = binding.edtName.getText().toString().trim();
        String email = binding.edtEmail.getText().toString().trim();
        String phone = binding.edtPhone.getText().toString().trim();
        String empty = getString(R.string.error_display_empty);

        if (name.isEmpty()) {
            errorDisplay.showNameError(empty);
        } else if (email.isEmpty()) {
            errorDisplay.showEmailError(empty);
        } else if (phone.isEmpty()) {
            errorDisplay.showPhoneError(empty);
        } else {
            setIndicationData(name, phone, email);
        }

    }

    private void setIndicationData(String name, String phone, String email) {
        Indicacao indicacao = new Indicacao(
                userData[0],
                getCurrentDate(),
                userData[1],
                userData[2],
                userData[3],
                userData[4],
                userData[5],
                name,
                phone,
                email
        );
        setIndicationPostData(indicacao);
    }

    private void setIndicationPostData(Indicacao indicacao) {
        IndicacaoEnvio indicacaoEnvio = new IndicacaoEnvio(
                indicacao,
                POST_INDICATION_SENDER,
                POST_INDICATION_COPY
        );
        convertedIndicationPostToJson(indicacaoEnvio);
    }

    private void convertedIndicationPostToJson(IndicacaoEnvio indicacaoEnvio) {
        Gson gson = new Gson();
        String json = gson.toJson(indicacaoEnvio);

        postIndication(json);
    }

    private void postIndication(String json) {
        apiService.postIndicationJsonToApi(json, this);
    }

    @Override
    public void onSuccessIndication(JsonObject Indication) {
        Log.i("onSuccessIndication", Indication.toString());
        Toast.makeText(this, "Indicação enviada com sucesso", Toast.LENGTH_SHORT).show();
        startHomeActivity();
    }

    @Override
    public void onErrorIndication(Exception e) {

    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(EXTRA_USER_TYPE, userType);
        intent.putExtra(EXTRA_USER_ID, userID);
        startActivity(intent);
        finish();
    }

}