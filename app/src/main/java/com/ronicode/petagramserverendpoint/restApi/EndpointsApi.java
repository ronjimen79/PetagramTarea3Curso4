package com.ronicode.petagramserverendpoint.restApi;

import com.ronicode.petagramserverendpoint.notificaciones.model.UsuarioResponse;
import com.ronicode.petagramserverendpoint.restApi.model.MascotasResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Roni on 10/06/2017.
 */

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_FOLOWS_MEDIA_RECENT)
    Call<MascotasResponse> getRecentMedia(@Path("user-id") String id);

    @GET(ConstantesRestApi.URL_GET_PROFILE_USER_WITH_ID)
    Call<MascotasResponse> getProfile(@Path("user-id") String id);

    @GET(ConstantesRestApi.URL_GET_FOLLOWS)
    Call<MascotasResponse> getFollows();

    @GET(ConstantesRestApi.URL_GET_FOLOWS_MEDIA_RECENT)
    Call<MascotasResponse> getFollowsMediaRecent(@Path("user-id") String id);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenId(@Field("id_dispositivo") String id_dispositivo,
                                           @Field("id_usuario_instagram") String id_usuario_instagram);

}
