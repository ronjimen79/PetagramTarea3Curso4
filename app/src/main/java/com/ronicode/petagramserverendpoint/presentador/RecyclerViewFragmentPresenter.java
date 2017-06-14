package com.ronicode.petagramserverendpoint.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ronicode.petagramserverendpoint.db.BaseDatos;
import com.ronicode.petagramserverendpoint.db.ConstructorMascotas;
import com.ronicode.petagramserverendpoint.fragments.IRecyclerViewFragmentView;
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

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascotas> detalles;
    private BaseDatos db;
    public String id;
    public String username;
    SharedPreferences PreferenciaFollows;
    SharedPreferences.Editor editor;
    private ArrayList<Mascotas> FollowsMedia;

    public RecyclerViewFragmentPresenter (IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context, int i2){

        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerFollows();

    }

    @Override
    public void inicializarListaMascota() {

        constructorMascotas = new ConstructorMascotas(context);
        detalles = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();

    }

    @Override
    public void mostrarMascotasRV() {

        iRecyclerViewFragmentView.inicializarMascotasAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(detalles));
        iRecyclerViewFragmentView.generarGridLayout();

    }

    @Override
    public void iniciar() {

        db = new BaseDatos(context);
        detalles = db.obtenerTodosLasMascotas();
        mostrarMascotasRV();

    }

    @Override
    public void obtenerFollows() {

        RestApiAdaptador restApiAdaptador = new RestApiAdaptador();
        Gson gsonFollows = restApiAdaptador.construyeGsonDeserializadorFollows();
        EndpointsApi endpointsApi = restApiAdaptador.establecerConexionRestApiInstagram(gsonFollows);
        Call<MascotasResponse> FollowsResponseCall = endpointsApi.getFollows();

        FollowsResponseCall.enqueue(new Callback<MascotasResponse>() {

            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {

                MascotasResponse mascotasResponse = response.body();
                detalles = mascotasResponse.getMascotas();

                PreferenciaFollows = context.getSharedPreferences("FollowsSandbox", Context.MODE_PRIVATE);
                editor = PreferenciaFollows.edit();
                editor.putString("id", "");
                editor.putString("username", "");
                editor.commit();

                for (int i = 0; i <detalles.size(); i++){

                    id = detalles.get(i).getId();
                    username = detalles.get(i).getNombreCompleto();
                    obtenerMediaRecentFollows();
                    crearFavorito();
                }

                detalles.clear();

            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {

                Toast.makeText(context, "Error de conexión... Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());

            }
        });

    }

    @Override
    public void obtenerMediaRecentFollows() {

        RestApiAdaptador restApiAdaptador = new RestApiAdaptador();
        Gson gsonFollowsMediaRecent = restApiAdaptador.construyeGsonDeserializadorFollowsMediaRecent();
        EndpointsApi endpointsApi = restApiAdaptador.establecerConexionRestApiInstagram(gsonFollowsMediaRecent);
        Call<MascotasResponse> FollowsMediaRecentResponseCall = endpointsApi.getFollowsMediaRecent(id);

        FollowsMediaRecentResponseCall.enqueue(new Callback<MascotasResponse>() {

            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {

                MascotasResponse mascotasResponse = response.body();
                FollowsMedia = mascotasResponse.getMascotas();
                detalles.addAll(FollowsMedia);
                mostrarMascotasRV();

            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {

                Toast.makeText(context, "ERROR AL CARGAR LAS FOTOS", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());

            }
        });

    }

    @Override
    public void crearFavorito() {

        editor.putString("id", PreferenciaFollows.getString("id", "")+id+",");
        editor.putString("username", PreferenciaFollows.getString("username", "")+username+",");
        editor.commit();

    }
}
