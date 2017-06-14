package com.ronicode.petagramserverendpoint.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ronicode.petagramserverendpoint.db.BaseDatos;
import com.ronicode.petagramserverendpoint.db.ConstructorMascotas;
import com.ronicode.petagramserverendpoint.fragments.IPerfilFragmentView;
import com.ronicode.petagramserverendpoint.pojo.Mascotas;
import com.ronicode.petagramserverendpoint.restApi.EndpointsApi;
import com.ronicode.petagramserverendpoint.restApi.adaptador.RestApiAdaptador;
import com.ronicode.petagramserverendpoint.restApi.model.MascotasResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roni on 10/06/2017.
 */

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {

    private IPerfilFragmentView iPerfilFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascotas> detalles;
    private BaseDatos db;
    public static String id;
    public static String m;
    SharedPreferences sharedPreferencesID;
    public static String v1;
    public static String v2;

    public PerfilFragmentPresenter (IPerfilFragmentView iPerfilFragmentView, Context context){

        this.iPerfilFragmentView = iPerfilFragmentView;
        this.context = context;
        sharedPreferencesID = context.getSharedPreferences("IdSearch", Context.MODE_PRIVATE);
        obtenerInformacionPerfil();
        obtenerMediosRecientes();
    }

    @Override
    public void inicializarListaMascota() {

        constructorMascotas = new ConstructorMascotas(context);
        detalles = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();

    }

    @Override
    public void obtenerMediosRecientes() {

        RestApiAdaptador restApiAdaptador = new RestApiAdaptador();
        Gson gsonMediaRecent = restApiAdaptador.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdaptador.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotasResponse> mascotasResponseCall = endpointsApi.getRecentMedia(sharedPreferencesID.getString("id", "5493721023"));

        mascotasResponseCall.enqueue(new Callback<MascotasResponse>() {

            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {

                MascotasResponse mascotasResponse = response.body();
                detalles = mascotasResponse.getMascotas();
                mostrarMascotasRV();

            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {

                Toast.makeText(context, "Error de conexión... Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());

            }
        });

    }

    @Override
    public void obtenerInformacionPerfil() {

        RestApiAdaptador restApiAdaptador = new RestApiAdaptador();
        Gson gsonInfoProfile = restApiAdaptador.construyeGsonDeserializadorInfoProfile();
        EndpointsApi endpointsApi = restApiAdaptador.establecerConexionRestApiInstagram(gsonInfoProfile);
        Call<MascotasResponse> mascotasResponseCall = endpointsApi.getProfile(sharedPreferencesID.getString("id", "5493721023"));

        mascotasResponseCall.enqueue(new Callback<MascotasResponse>() {

            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {

                MascotasResponse mascotasResponse = response.body();
                detalles = mascotasResponse.getMascotas();

            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {

                Toast.makeText(context, "ERROR AL CARGAR FOTO DEL PERFIL", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());

            }
        });

    }

    @Override
    public void mostrarMascotasRV() {

        iPerfilFragmentView.inicializarMascotasAdaptadorRV(iPerfilFragmentView.crearAdaptador(detalles));
        iPerfilFragmentView.generarGridLayout();
    }

}
