package com.ronicode.petagramserverendpoint;


import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ronicode.petagramserverendpoint.correo.EnviarCorreo;

public class Contacto extends AppCompatActivity implements View.OnClickListener {

    Button btnEnviar;
    TextInputEditText tieNombre;
    TextInputEditText tieEmail;
    TextInputEditText tieMensaje;
    ImageView imgFavoritoAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        imgFavoritoAB = (ImageView) findViewById(R.id.imgFavoritoAB);
        imgFavoritoAB.setVisibility(View.INVISIBLE);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.dog_footprint_filled);

        btnEnviar = (Button) findViewById(R.id.btnEnviarcomentario);

        btnEnviar.setOnClickListener(this);
    }

    private void EnviarCorreo(){

        tieNombre = (TextInputEditText) findViewById(R.id.tieNombre);
        tieEmail = (TextInputEditText) findViewById(R.id.tieEmail);
        tieMensaje = (TextInputEditText) findViewById(R.id.tieMensaje);

        String nombre = tieNombre.getText().toString().trim();
        String email = tieEmail.getText().toString().trim();
        String mensaje = tieMensaje.getText().toString().trim();

        EnviarCorreo ec = new  EnviarCorreo(this, email, this.getResources().getString(R.string.tema), "Hablate "+ nombre + ", " + mensaje);

        ec.execute();
    }

    @Override
    public void onClick(View view) {
        EnviarCorreo();
    }
}
