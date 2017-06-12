package com.ronicode.petagramserverendpoint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.ronicode.petagramserverendpoint.adaptadores.PageAdaptador;
import com.ronicode.petagramserverendpoint.fragments.DetalleMascota;
import com.ronicode.petagramserverendpoint.fragments.PerfilFragment;
import com.ronicode.petagramserverendpoint.fragments.RecyclerViewFragment;
import com.ronicode.petagramserverendpoint.notificaciones.model.UsuarioResponse;
import com.ronicode.petagramserverendpoint.restApi.EndpointsApi;
import com.ronicode.petagramserverendpoint.restApi.adaptador.RestApiAdaptador;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaMascota extends AppCompatActivity {

    private Toolbar ActionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Bundle bundle;
    private SharedPreferences preferenciaID;
    SharedPreferences.Editor editor;
    private String id, token, usuario;
    private static final String TAG = "FIREBASE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        bundle = getIntent().getExtras();

        if (bundle != null){

            preferenciaID = getSharedPreferences("IdSearch", Context.MODE_PRIVATE);
            editor = preferenciaID.edit();
            editor.putString("id", bundle.getString("id"));
            editor.commit();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascota);

        ActionBar = (Toolbar) findViewById(R.id.miActionBar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewpager();

        if (ActionBar != null){
            setSupportActionBar(ActionBar);
        }

        getSupportActionBar().setIcon(R.drawable.dog_footprint_filled);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public void verFavoritos(View v){
        Intent abrir = new Intent(this, DetalleMascota.class);
        startActivity(abrir);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itmenuContacto:
                Intent intentContacto = new Intent(this, Contacto.class);
                startActivity(intentContacto);
                break;

            case R.id.itmenuAcercade:
                Intent intentAcercaDe = new Intent(this, AcercaDe.class);
                startActivity(intentAcercaDe);
                break;

            case R.id.itmenuConfigurarcuenta:
                Intent intentConfigurarCuenta = new Intent(this, ConfigurarCuenta.class);
                startActivity(intentConfigurarCuenta);
                break;

            case R.id.itmenuRecibirnotificaciones:
                obtenerDatos();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void obtenerDatos(){

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "ID_DISPOSITIVO: " + token);
        Log.d(TAG, "ID_USUARIO_INSTAGRAM: " + id);

        RestApiAdaptador restApiAdaptador = new RestApiAdaptador();
        EndpointsApi endpointsApi = restApiAdaptador.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.registrarTokenId(token, id);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

    private ArrayList<Fragment> adicionarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpViewpager() {

        viewPager.setAdapter(new PageAdaptador(getSupportFragmentManager(), adicionarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home_48);
        tabLayout.getTabAt(1).setIcon(R.drawable.perfil_48);
    }
}
