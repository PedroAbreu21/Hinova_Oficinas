package com.example.hinovaoficinas.network.authentication.callback;

public interface AuthenticationIsLoggedCallback {
    void onSuccessIsLogged(String userID);
    void onErrorIsLogged();
}
