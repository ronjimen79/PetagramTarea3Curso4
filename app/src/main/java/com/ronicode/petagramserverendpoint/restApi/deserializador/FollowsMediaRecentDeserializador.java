package com.ronicode.petagramserverendpoint.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ronicode.petagramserverendpoint.pojo.Mascotas;
import com.ronicode.petagramserverendpoint.restApi.JsonKeys;
import com.ronicode.petagramserverendpoint.restApi.model.MascotasResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Roni on 10/06/2017.
 */

public class FollowsMediaRecentDeserializador implements JsonDeserializer<MascotasResponse> {

    @Override
    public MascotasResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        MascotasResponse mascotasResponse = gson.fromJson(json, MascotasResponse.class);

        JsonArray mascotasResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotasResponse.setMascotas(deserealizarMediaFollowsDeJson(mascotasResponseData));
        return mascotasResponse;
    }

    private ArrayList<Mascotas> deserealizarMediaFollowsDeJson (JsonArray mascotasResponseData){

        ArrayList<Mascotas> mascotas = new ArrayList<>();

        for (int i = 0; i <mascotasResponseData.size(); i++){

            JsonObject mascotasResponseDataObject = mascotasResponseData.get(i).getAsJsonObject();
            JsonObject userJson = mascotasResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();

            JsonObject imageJson = mascotasResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = mascotasResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Mascotas MediafollowActual = new Mascotas();
            MediafollowActual.setId(id);
            MediafollowActual.setUrlFoto(urlFoto);
            MediafollowActual.setLikes(likes);

            mascotas.add(MediafollowActual);
        }

        return mascotas;

    }
}
