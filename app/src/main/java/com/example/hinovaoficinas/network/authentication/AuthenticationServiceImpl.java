package com.example.hinovaoficinas.network.authentication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.hinovaoficinas.network.authentication.callback.AuthenticationIsLoggedCallback;
import com.example.hinovaoficinas.network.authentication.callback.AuthenticationRegisterCallback;
import com.example.hinovaoficinas.network.authentication.callback.AuthenticationSignInCallback;
import com.example.hinovaoficinas.network.firestore.FirestoreService;
import com.example.hinovaoficinas.network.firestore.FirestoreServiceImpl;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirestoreService firestoreService;
    private final Context context;

    public AuthenticationServiceImpl(Context context) {
        this.context = context;
        firestoreService = new FirestoreServiceImpl(context);
    }

    @Override
    public void signInFirebase(String email, String password, AuthenticationSignInCallback callback) {
        auth.signInWithEmailAndPassword(
                email, password
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                callback.onSuccessSingIn(userID);
            } else {
                try {
                    throw task.getException();
                } catch (FirebaseAuthInvalidUserException e) {
                    callback.onErrorSignIn(0);
                } catch (FirebaseAuthInvalidCredentialsException e) {
                    callback.onErrorSignIn(1);
                } catch (FirebaseNetworkException e) {
                    callback.onErrorSignIn(2);
                } catch (FirebaseTooManyRequestsException e) {
                    callback.onErrorSignIn(3);
                } catch (Exception e) {
                    callback.onErrorSignIn(4);
                }
            }

        });
    }

    @Override
    public void registerUserFirebase(String email, String password, AuthenticationRegisterCallback callback) {
        FirebaseUser originalUser = auth.getCurrentUser();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userID = auth.getCurrentUser().getUid();
                        callback.onRegisterSuccess(userID, originalUser);
                    } else {
                        try {
                            throw (Objects.requireNonNull(task.getException()));
                        } catch (FirebaseAuthWeakPasswordException e) {
                            callback.onRegisterError(0);
                        } catch (FirebaseAuthUserCollisionException e) {
                            callback.onRegisterError(1);
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            callback.onRegisterError(2);
                        } catch (Exception e) {
                            callback.onRegisterError(3);
                        }
                    }
                });
    }

    @Override
    public void recoverLoginFirebase(FirebaseUser origialUser) {
        auth.updateCurrentUser(origialUser);
    }

    @Override
    public void signOutFirebase() {
        auth.signOut();
    }

    @Override
    public void checkUserLogged(AuthenticationIsLoggedCallback callback) {
        if ((auth.getCurrentUser() != null)) {
            callback.onSuccessIsLogged(auth.getCurrentUser().getUid());
        } else {
            callback.onErrorIsLogged();
        }
    }
}