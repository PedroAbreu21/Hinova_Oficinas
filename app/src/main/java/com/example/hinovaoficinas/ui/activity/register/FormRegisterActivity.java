package com.example.hinovaoficinas.ui.activity.register;

import static com.example.hinovaoficinas.utils.Constants.EXTRA_USER_ID;
import static com.example.hinovaoficinas.utils.Constants.EXTRA_USER_TYPE;
import static com.example.hinovaoficinas.utils.Constants.TAG_ERROR_FIRESTORE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hinovaoficinas.R;
import com.example.hinovaoficinas.databinding.ActivityFormRegisterBinding;
import com.example.hinovaoficinas.ui.activity.home.HomeActivity;

public class FormRegisterActivity extends AppCompatActivity {

    private ActivityFormRegisterBinding binding;
    private RegisterManager registerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registerManager = new RegisterManager(getApplicationContext(), this);

        binding.btnRegister.setOnClickListener(v -> registerManager.validateData(
                binding.edtName.getText().toString().trim(),
                binding.edtEmail.getText().toString().trim(),
                binding.edtCpf.getText().toString().trim(),
                binding.edtPhone.getText().toString().trim(),
                binding.edtPlate.getText().toString().trim(),
                binding.edtPassword.getText().toString().trim()
        ));

    }

    public void showNameError(String errorMessage) {
        binding.edtName.setError(errorMessage);
    }

    public void showEmailError(String errorMessage) {
        binding.edtEmail.setError(errorMessage);
    }

    public void showCpfError(String errorMessage) {
        binding.edtCpf.setError(errorMessage);
    }

    public void showPhoneError(String errorMessage) {
        binding.edtPhone.setError(errorMessage);
    }

    public void showPlateError(String errorMessage) {
        binding.edtPlate.setError(errorMessage);
    }

    public void showPasswordError(String errorMessage) {
        binding.edtPassword.setError(errorMessage);
    }

    public void registerSuccessful(String originalUser) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(EXTRA_USER_TYPE, 0L);
        intent.putExtra(EXTRA_USER_ID, originalUser);
        startActivity(intent);
        finish();
    }

    public void registerError(int error) {
        String[] errors = getResources().getStringArray(R.array.errorArrayRegister);
        Log.e(TAG_ERROR_FIRESTORE, errors[error]);
        Toast.makeText(this, errors[error], Toast.LENGTH_SHORT).show();
    }

}
