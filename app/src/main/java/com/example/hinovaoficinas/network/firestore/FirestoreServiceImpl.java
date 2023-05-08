package com.example.hinovaoficinas.network.firestore;

import static com.example.hinovaoficinas.utils.Constants.DB_USER_ASSOCIATION;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_COLLECTION;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_CPF;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_NAME;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_PHONE;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_PLATE;
import static com.example.hinovaoficinas.utils.Constants.DB_USER_TYPE;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.hinovaoficinas.network.firestore.callback.FirestoreSaveUserCallback;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreTypeUserCallback;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreUserInformationCallback;
import com.example.hinovaoficinas.network.firestore.callback.FirestoreUserNameCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirestoreServiceImpl implements FirestoreService {
    private final FirebaseFirestore userDB = FirebaseFirestore.getInstance();
    private final Context context;

    public FirestoreServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getUserTypeFromDB(String userID, FirestoreTypeUserCallback callback) {
        DocumentReference userDocRef = userDB.collection(DB_USER_COLLECTION).document(userID);

        userDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                Long userType = documentSnapshot.getLong(DB_USER_TYPE);
                callback.onSuccessGetType(userType);
            }
        }).addOnFailureListener(callback::onErrorGetType);
    }

    @Override
    public void getUserNameFromDB(String userID, FirestoreUserNameCallback callback) {
        DocumentReference userDocRef = userDB.collection(DB_USER_COLLECTION).document(userID);
        userDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String userName = documentSnapshot.getString(DB_USER_NAME);
                callback.onSuccessGetUserName(userName);
            }
        }).addOnFailureListener(callback::onErrorGetUserName);
    }

    @Override
    public void getUserInformationFromDB(String userID, FirestoreUserInformationCallback callback) {
        String[] userData = new String[6];
        DocumentReference userDocRef = userDB.collection(DB_USER_COLLECTION).document(userID);
        userDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                userData[0] = Objects.requireNonNull(documentSnapshot.getLong(DB_USER_ASSOCIATION)).toString();
                userData[1] = Objects.requireNonNull(documentSnapshot.getLong(DB_USER_CPF)).toString();
                userData[2] = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
                userData[3] = documentSnapshot.getString(DB_USER_NAME);
                userData[4] = Objects.requireNonNull(documentSnapshot.getLong(DB_USER_PHONE)).toString();
                userData[5] = documentSnapshot.getString(DB_USER_PLATE);
                callback.onSuccessGetInformation(userData);
            }
        }).addOnFailureListener(callback::onErrorGetInformation);
    }

    @Override
    public void saveUserDataFromDB(String name, long cpf, long phone, String plate, String userID, FirestoreSaveUserCallback callback) {

        Map<String, Object> user = new HashMap<>();
        user.put("association", 601);
        user.put("type", 1);
        user.put("name", name);
        user.put("cpf", cpf);
        user.put("phone", phone);
        user.put("plate", plate);

        DocumentReference userDocRef = userDB.collection("user").document(userID);
        userDocRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                callback.onSuccessSaveUser();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onErrorSaveUser(e);
            }
        });

    }

}