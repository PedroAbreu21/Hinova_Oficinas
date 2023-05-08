package com.example.hinovaoficinas.network.workshopApi;

import com.example.hinovaoficinas.network.workshopApi.callback.ApiIndicationCallback;
import com.example.hinovaoficinas.network.workshopApi.callback.ApiWorkshopCallback;

public interface ApiService {
    void postIndicationJsonToApi(String json, ApiIndicationCallback callback);
    void getWorkShopsApi(ApiWorkshopCallback callback);
}
