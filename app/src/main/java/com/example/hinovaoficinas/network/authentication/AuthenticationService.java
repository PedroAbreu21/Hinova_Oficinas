package com.example.hinovaoficinas.network.authentication;

import com.example.hinovaoficinas.network.authentication.callback.AuthenticationIsLoggedCallback;
import com.example.hinovaoficinas.network.authentication.callback.AuthenticationRegisterCallback;
import com.example.hinovaoficinas.network.authentication.callback.AuthenticationSignInCallback;
import com.google.firebase.auth.FirebaseUser;

public interface AuthenticationService {
    void signInFirebase(String email, String password, AuthenticationSignInCallback callback);
    void signOutFirebase();
    void checkUserLogged(AuthenticationIsLoggedCallback callback);
    void registerUserFirebase(String email, String password, AuthenticationRegisterCallback callback);
    void recoverLoginFirebase(FirebaseUser origialUser);
}
