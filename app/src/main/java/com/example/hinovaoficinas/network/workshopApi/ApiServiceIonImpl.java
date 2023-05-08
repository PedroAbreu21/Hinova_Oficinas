package com.example.hinovaoficinas.network.workshopApi;

import static com.example.hinovaoficinas.utils.Constants.API_BASE_URL;
import static com.example.hinovaoficinas.utils.Constants.API_CONTENT_TYPE_JSON;
import static com.example.hinovaoficinas.utils.Constants.API_ENDPOINT_INDICATION;
import static com.example.hinovaoficinas.utils.Constants.API_ENDPOINT_WORKSHOPS;

import android.content.Context;

import com.example.hinovaoficinas.data.models.workshop.WorkshopDTO;
import com.example.hinovaoficinas.network.workshopApi.callback.ApiIndicationCallback;
import com.example.hinovaoficinas.network.workshopApi.callback.ApiWorkshopCallback;
import com.google.gson.Gson;
import com.koushikdutta.ion.Ion;

public class ApiServiceIonImpl implements ApiService {
    private final Context context;

    public ApiServiceIonImpl(Context context) {
        this.context = context;
    }

    @Override
    public void postIndicationJsonToApi(String json, ApiIndicationCallback callback) {
        Ion.with(context)
                .load(API_BASE_URL + API_ENDPOINT_INDICATION)
                .setHeader("Content-Type", API_CONTENT_TYPE_JSON)
                .setStringBody(json)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (e != null) {
                        callback.onErrorIndication(e);
                        return;
                    }
                    callback.onSuccessIndication(result);
                });
    }

    @Override
    public void getWorkShopsApi(ApiWorkshopCallback callback) {
        Ion.with(context)
                .load(API_BASE_URL + API_ENDPOINT_WORKSHOPS)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (e != null) {
                        callback.onApiError(e);
                        return;
                    }
                    Gson gson = new Gson();
                    WorkshopDTO workShops = gson.fromJson(result, WorkshopDTO.class);
                    callback.onWorkShopSuccess(workShops);
                });
    }
}