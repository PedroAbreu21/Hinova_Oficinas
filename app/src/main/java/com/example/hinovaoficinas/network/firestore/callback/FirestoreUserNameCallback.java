package com.example.hinovaoficinas.network.firestore.callback;

public interface FirestoreUserNameCallback {
    void onSuccessGetUserName(String userName);
    void onErrorGetUserName(Exception e);
}
