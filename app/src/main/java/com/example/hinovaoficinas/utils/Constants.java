package com.example.hinovaoficinas.utils;

public class Constants {
    public static final String EXTRA_USER_TYPE = "userType";
    public static final String EXTRA_USER_ID = "userID";
    public static final String EXTRA_TYPE_LOGIN = "typeLogin";
    public static final String EXTRA_WORKSHOP = "workshop";

    public static final String API_BASE_URL = "http://app.hinovamobile.com.br/ProvaConhecimentoWebApi";
    public static final String API_ENDPOINT_WORKSHOPS = "/Api/Oficina?codigoAssociacao=601";
    public static final String API_ENDPOINT_INDICATION = "/Api/Indicacao";
    public static final String API_CONTENT_TYPE_JSON = "application/json";

    public static final String POST_INDICATION_SENDER = "romulo.marques@hinovamobile.com.br";
    public static final String[] POST_INDICATION_COPY = null;

    public static final String REGEX_NEWLINE = "(\\\\r\\\\n|\\\\n)";
    public static final String SUBSTITUTION_NEWLINE  = "\\\n";

    public static final String DB_USER_COLLECTION = "user";
    public static final String DB_USER_ASSOCIATION = "association";
    public static final String DB_USER_CPF = "cpf";
    public static final String DB_USER_NAME = "name";
    public static final String DB_USER_PHONE = "phone";
    public static final String DB_USER_PLATE = "plate";
    public static final String DB_USER_TYPE = "type";

    public static final String IMG_PREFIX = "img_";
    public static final String IMG_SUFFIX = ".jpg";

    public static final String SERIALIZED_WORKSHOP_LIST = "ListaOficinas";
    public static final String SERIALIZED_WORKSHOP_ID = "Id";
    public static final String SERIALIZED_WORKSHOP_NAME = "Nome";
    public static final String SERIALIZED_WORKSHOP_DESCRIPTION = "Descricao";
    public static final String SERIALIZED_WORKSHOP_SHORT_DESCRIPTION = "DescricaoCurta";
    public static final String SERIALIZED_WORKSHOP_LOCALE = "Endereco";
    public static final String SERIALIZED_WORKSHOP_LATITUDE = "Latitude";
    public static final String SERIALIZED_WORKSHOP_LONGITUDE = "Longitude";
    public static final String SERIALIZED_WORKSHOP_IMAGE = "Foto";
    public static final String SERIALIZED_WORKSHOP_RATING = "AvaliacaoUsuario";
    public static final String SERIALIZED_WORKSHOP_ASSOCIATION = "CodigoAssociacao";
    public static final String SERIALIZED_WORKSHOP_EMAIL = "Email";
    public static final String SERIALIZED_WORKSHOP_PHONE1 = "Telefone1";
    public static final String SERIALIZED_WORKSHOP_PHONE2 = "Telefone2";
    public static final String SERIALIZED_WORKSHOP_ENABLE = "Ativo";

    public static final String TAG_ERROR_FIRESTORE = "ERROR_FIRESTORE";
}
