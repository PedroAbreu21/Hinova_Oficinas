package com.example.hinovaoficinas.ui.activity.register;

import android.content.Context;
import android.widget.Toast;

import com.example.hinovaoficinas.network.authentication.AuthenticationService;
import com.example.hinovaoficinas.network.authentication.AuthenticationServiceImpl;
import com.example.hinovaoficinas.network.authentication.callback.AuthenticationRegisterCallback;
import com.example.hinovaoficinas.network.firestore.FirestoreService;
import com.example.hinovaoficinas.network.firestore.FirestoreServiceImpl;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreSaveUserCallback;
import com.google.firebase.auth.FirebaseUser;

public class RegisterManager implements FirestoreSaveUserCallback, AuthenticationRegisterCallback {

    private final FirestoreService firestoreService;
    private final AuthenticationService authenticationService;

    private final FormRegisterActivity activity;
    private String name;
    private long cpf;
    private long phone;
    private String plate;

    private FirebaseUser originalUser;

    public RegisterManager(Context context, FormRegisterActivity activity) {
        this.activity = activity;
        authenticationService = new AuthenticationServiceImpl(context);
        firestoreService = new FirestoreServiceImpl(context);
    }

    public void validateData(String name, String email, String cpf, String phone, String plate, String password) {
        if (name.isEmpty()) {
            activity.showNameError("Campo obrigatório.");
            return;
        }

        if (email.isEmpty()) {
            activity.showEmailError("Campo obrigatório.");
            return;
        }

        if (cpf.isEmpty()) {
            activity.showCpfError("Campo obrigatório.");
            return;
        }

        if (phone.isEmpty()) {
            activity.showPhoneError("Campo obrigatório.");
            return;
        }

        if (plate.isEmpty()) {
            activity.showPlateError("Campo obrigatório.");
            return;
        }

        if (password.isEmpty()) {
            activity.showPasswordError("Campo obrigatório.");
            return;
        }

        this.name = name;
        this.cpf = Long.parseLong(cpf);
        this.phone = Long.parseLong(phone);
        this.plate = plate;

        authenticationService.registerUserFirebase(email, password, this);
    }

    @Override
    public void onRegisterSuccess(String userID, FirebaseUser originalUser) {
        this.originalUser = originalUser;
        firestoreService.saveUserDataFromDB(name, cpf, phone, plate, userID, this);
    }

    @Override
    public void onRegisterError(int error) {
        activity.registerError(error);
    }

    @Override
    public void onSuccessSaveUser() {
        authenticationService.recoverLoginFirebase(originalUser);
        Toast.makeText(activity, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
        activity.registerSuccessful(originalUser.getUid());
    }

    @Override
    public void onErrorSaveUser(Exception e) {
        Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show();
    }

}
