package com.example.hinovaoficinas.network.firestore.callback;

public interface FirestoreUserInformationCallback {
    void onSuccessGetInformation(String[] userData);
    void onErrorGetInformation(Exception e);
}
