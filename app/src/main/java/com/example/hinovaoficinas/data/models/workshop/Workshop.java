package com.example.hinovaoficinas.data.models.workshop;

import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_ASSOCIATION;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_DESCRIPTION;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_EMAIL;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_ENABLE;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_ID;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_IMAGE;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_LATITUDE;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_LOCALE;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_LONGITUDE;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_NAME;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_PHONE1;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_PHONE2;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_RATING;
import static com.example.hinovaoficinas.utils.Constants.SERIALIZED_WORKSHOP_SHORT_DESCRIPTION;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Workshop implements Serializable {

    @SerializedName(SERIALIZED_WORKSHOP_ID)
    public Integer Id;

    @SerializedName(SERIALIZED_WORKSHOP_NAME)
    public String Name;

    @SerializedName(SERIALIZED_WORKSHOP_DESCRIPTION)
    public String Description;

    @SerializedName(SERIALIZED_WORKSHOP_SHORT_DESCRIPTION)
    public String ShortDescription;

    @SerializedName(SERIALIZED_WORKSHOP_LOCALE)
    public String Locale;

    @SerializedName(SERIALIZED_WORKSHOP_LATITUDE)
    public Double Latitude;

    @SerializedName(SERIALIZED_WORKSHOP_LONGITUDE)
    public Double Longitude;

    @SerializedName(SERIALIZED_WORKSHOP_IMAGE)
    public String Image;

    @SerializedName(SERIALIZED_WORKSHOP_RATING)
    public Integer Rating;

    @SerializedName(SERIALIZED_WORKSHOP_ASSOCIATION)
    public Integer AssociationCode;

    @SerializedName(SERIALIZED_WORKSHOP_EMAIL)
    public String Email;

    @SerializedName(SERIALIZED_WORKSHOP_PHONE1)
    public String Phone1;

    @SerializedName(SERIALIZED_WORKSHOP_PHONE2)
    public String Phone2;

    @SerializedName(SERIALIZED_WORKSHOP_ENABLE)
    public boolean Enable;

}
