package com.example.hinovaoficinas.data.models.workshop;

import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_LIST;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkshopDTO {

    @SerializedName(SERIALIZED_WORKSHOP_LIST)
    public List<Workshop> Workshop;

}
