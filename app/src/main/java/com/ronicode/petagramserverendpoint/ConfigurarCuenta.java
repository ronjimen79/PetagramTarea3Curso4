package com.ronicode.petagramserverendpoint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ronicode.petagramserverendpoint.presentador.PerfilFragmentPresenter;

public class ConfigurarCuenta extends AppCompatActivity {

    private Context context;
    ImageView imgFavoritoAB;
    EditText tieUsuario;
    public static String usuario;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        imgFavoritoAB = (ImageView) findViewById(R.id.imgFavoritoAB);
        imgFavoritoAB.setVisibility(View.INVISIBLE);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.dog_footprint_filled);
    }

    public void guardarUsuario (View view){

        tieUsuario = (EditText) findViewById(R.id.tieUsuario);
        usuario = tieUsuario.getText().toString();
        leerFollows();

    }

    public void leerFollows(){

        SharedPreferences FollowPreferencia = getSharedPreferences("FollowsSandbox", Context.MODE_PRIVATE);
        String ArregloUserName[] = FollowPreferencia.getString("username", "").split(",");
        String ArregloId[] = FollowPreferencia.getString("id", "").split(",");

        for (int i = 0; i <ArregloUserName.length ; i++){

            x = 1;

            if (usuario.equals(ArregloUserName[i])){
                PerfilFragmentPresenter.id = ArregloId[i];

                Intent intent = new Intent(ConfigurarCuenta.this, ListaMascota.class);
                intent.putExtra("id", ArregloId[i]);
                startActivity(intent);
                finish();
            }
            else {
                x = 0;
            }
        }

        if (x == 0){

            Toast.makeText(ConfigurarCuenta.this," No existe: "+usuario+ " como usuario Sandbox", Toast.LENGTH_LONG).show();
        }

    }
}
