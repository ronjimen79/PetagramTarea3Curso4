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

public class FollowsDeserializador implements JsonDeserializer<MascotasResponse> {

    @Override
    public MascotasResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotasResponse mascotasResponse = gson.fromJson(json, MascotasResponse.class);

        JsonArray mascotasResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotasResponse.setMascotas(deserealizarFollowsDeJson(mascotasResponseData));

        return mascotasResponse;
    }

    private ArrayList<Mascotas> deserealizarFollowsDeJson(JsonArray mascotasResponseData){

        ArrayList<Mascotas> mascotas = new ArrayList<>();

        for (int i = 0; 1 <mascotasResponseData.size(); i++){

            JsonObject mascotasResponseDataObject = mascotasResponseData.get(i).getAsJsonObject();
            String id = mascotasResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String username = mascotasResponseDataObject.get(JsonKeys.USERNAME).getAsString();

            Mascotas followActual = new Mascotas();
            followActual.setId(id);
            followActual.setNombreCompleto(username);

            mascotas.add(followActual);
        }

        return mascotas;
    }
}
