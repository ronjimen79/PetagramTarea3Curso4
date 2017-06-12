package com.ronicode.petagramserverendpoint.restApi;

/**
 * Created by Roni on 10/06/2017.
 */

public final class ConstantesRestApi {

    //https://api.instagram.com/v1

    public final static String VERSION = "/v1/";
    public final static String ROOT_URL = "https://api.instagram.com" + VERSION;
    public final static String ACCESS_TOKEN = "5493721023.f2e4d15.611e4be786ad4a51b677a6a1849e9b49";
    public final static String KEY_ACCESS_TOKEN = "?access_token=";
    public final static String KEY_GET_INFORMATION_USER = "users/self/media/recent/";
    public final static String USER_ID_MEDIA_RECENT = "users/{user-id}/";

    // https://api.instagram.com/v1/users/self/?access_token=ACCESS-TOKEN

    public final static String KEY_GET_PROFILE_USER = "users/self/";

    // https://api.instagram.com/v1/users/{user-id}/?access_token=ACCESS-TOKEN

    public final static String URL_GET_PROFILE_USER = KEY_GET_PROFILE_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public final static String KEY_GET_FOLLOWS = "follows";

    // https://api.instagram.com/v1/users/self/follows?access_token=ACCESS-TOKEN

    public final static String URL_GET_FOLLOWS = KEY_GET_PROFILE_USER + KEY_GET_FOLLOWS + KEY_ACCESS_TOKEN +ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN

    public final static String KEY_GET_USERS= "users/{user-id}/media/recent/";
    public final static String URL_GET_FOLOWS_MEDIA_RECENT = KEY_GET_USERS +KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //  https://api.instagram.com/v1/users/{user-id}/?access_token=ACCESS-TOKEN

    public final static String URL_GET_PROFILE_USER_WITH_ID = USER_ID_MEDIA_RECENT + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String ROOT_URL_TOKEN = "https://limitless-cliffs-10593.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario";

}
