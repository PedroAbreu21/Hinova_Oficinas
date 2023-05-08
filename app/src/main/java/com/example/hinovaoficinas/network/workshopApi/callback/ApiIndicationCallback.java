package com.example.hinovaoficinas.network.workshopApi.callback;

import com.google.gson.JsonObject;

public interface ApiIndicationCallback {
    void onSuccessIndication(JsonObject Indication);
    void onErrorIndication(Exception e);
}
