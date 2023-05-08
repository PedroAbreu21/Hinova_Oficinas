package com.example.hinovaoficinas.network.firestore.callback;

public interface FirestoreTypeUserCallback {
    void onSuccessGetType(Long userType);
    void onErrorGetType(Exception e);
}
