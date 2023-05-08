package com.example.hinovaoficinas.network.workshopApi.callback;

import com.example.hinovaoficinas.data.models.workshop.WorkshopDTO;

public interface ApiWorkshopCallback {
    void onWorkShopSuccess(WorkshopDTO workShops);
    void onApiError(Exception e);
}
