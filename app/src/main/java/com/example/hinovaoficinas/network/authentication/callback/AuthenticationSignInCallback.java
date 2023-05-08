package com.example.hinovaoficinas.network.authentication.callback;

public interface AuthenticationSignInCallback {
    void onSuccessSingIn(String userID);
    void onErrorSignIn(int errorID);
}
