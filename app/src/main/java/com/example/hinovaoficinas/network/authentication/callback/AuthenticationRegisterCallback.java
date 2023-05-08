package com.example.hinovaoficinas.network.authentication.callback;

import com.google.firebase.auth.FirebaseUser;

public interface AuthenticationRegisterCallback {
    void onRegisterSuccess(String userID, FirebaseUser originalUser);
    void onRegisterError(int error);
}
