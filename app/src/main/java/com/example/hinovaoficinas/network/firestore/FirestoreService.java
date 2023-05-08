package com.example.hinovaoficinas.network.firestore;

import com.example.hinovaoficinas.network.firestore.callback.FirestoreSaveUserCallback;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreTypeUserCallback;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreUserInformationCallback;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreUserNameCallback;

public interface FirestoreService {
    void getUserTypeFromDB(String userID, FirestoreTypeUserCallback callback);
    void getUserNameFromDB(String userID, FirestoreUserNameCallback callback);
    void getUserInformationFromDB(String userID, FirestoreUserInformationCallback callback);
    void saveUserDataFromDB(String name, long cpf, long phone, String plate, String userID, FirestoreSaveUserCallback callback);
}
