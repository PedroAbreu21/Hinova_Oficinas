package com.example.hinovaoficinas.utils.error;

public interface ErrorDisplay {
    void showNameError(String errorMessage);
    void showEmailError(String errorMessage);
    void showCpfError(String errorMessage);
    void showPhoneError(String errorMessage);
    void showPlateError(String errorMessage);
    void showPasswordError(String errorMessage);
}
