package com.ronicode.petagramserverendpoint.restApi.adaptador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ronicode.petagramserverendpoint.restApi.ConstantesRestApi;
import com.ronicode.petagramserverendpoint.restApi.EndpointsApi;
import com.ronicode.petagramserverendpoint.restApi.deserializador.FollowsDeserializador;
import com.ronicode.petagramserverendpoint.restApi.deserializador.FollowsMediaRecentDeserializador;
import com.ronicode.petagramserverendpoint.restApi.deserializador.FotoPerfilDeserializador;
import com.ronicode.petagramserverendpoint.restApi.deserializador.PerfilDeserializador;
import com.ronicode.petagramserverendpoint.restApi.model.MascotasResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Roni on 10/06/2017.
 */

public class RestApiAdaptador {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotasResponse.class, new PerfilDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorInfoProfile(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotasResponse.class, new FotoPerfilDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorFollows(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotasResponse.class, new FollowsDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorFollowsMediaRecent(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotasResponse.class, new FollowsMediaRecentDeserializador());

        return gsonBuilder.create();
    }

    public EndpointsApi establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_TOKEN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }
}
