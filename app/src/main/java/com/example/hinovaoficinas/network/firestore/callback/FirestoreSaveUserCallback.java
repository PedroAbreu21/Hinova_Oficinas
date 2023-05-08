package com.example.hinovaoficinas.network.firestore.callback;

public interface FirestoreSaveUserCallback {
    void onSuccessSaveUser();
    void onErrorSaveUser(Exception e);
}
