package com.example.hinovaoficinas.utils.error;

import android.widget.EditText;

public class ErrorDisplayImpl implements ErrorDisplay {
    private final EditText edtName;
    private final EditText edtEmail;
    private final EditText edtCpf;
    private final EditText edtPhone;
    private final EditText edtPlate;
    private final EditText edtPassword;

    public ErrorDisplayImpl(EditText edtName, EditText edtEmail, EditText edtCpf, EditText edtPhone, EditText edtPlate, EditText edtPassword) {
        this.edtName = edtName;
        this.edtEmail = edtEmail;
        this.edtCpf = edtCpf;
        this.edtPhone = edtPhone;
        this.edtPlate = edtPlate;
        this.edtPassword = edtPassword;
    }

    public ErrorDisplayImpl(EditText edtName, EditText edtEmail, EditText edtPhone) {
        this.edtName = edtName;
        this.edtEmail = edtEmail;
        this.edtCpf = null;
        this.edtPhone = edtPhone;
        this.edtPlate = null;
        this.edtPassword = null;
    }

    @Override
    public void showNameError(String errorMessage) {
        edtName.setError(errorMessage);
    }

    @Override
    public void showEmailError(String errorMessage) {
        edtEmail.setError(errorMessage);
    }

    @Override
    public void showCpfError(String errorMessage) {
        assert edtCpf != null;
        edtCpf.setError(errorMessage);
    }

    @Override
    public void showPhoneError(String errorMessage) {
        edtPhone.setError(errorMessage);
    }

    @Override
    public void showPlateError(String errorMessage) {
        assert edtPlate != null;
        edtPlate.setError(errorMessage);
    }

    @Override
    public void showPasswordError(String errorMessage) {
        assert edtPassword != null;
        edtPassword.setError(errorMessage);
    }
}

