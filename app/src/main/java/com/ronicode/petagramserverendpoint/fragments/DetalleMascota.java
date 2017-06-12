package com.ronicode.petagramserverendpoint.fragments;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.ronicode.petagramserverendpoint.R;
import com.ronicode.petagramserverendpoint.adaptadores.MascotasAdaptador;
import com.ronicode.petagramserverendpoint.pojo.Mascotas;
import com.ronicode.petagramserverendpoint.presentador.DetMascotaPresenter;
import com.ronicode.petagramserverendpoint.presentador.IDetMascotaPresenter;

import java.util.ArrayList;

public class DetalleMascota extends AppCompatActivity implements IDetalleMascota {

    private ImageView imgFavoritoAB;
    private RecyclerView rv;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager llm;
    private IDetMascotaPresenter presentador;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        Toolbar ActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(ActionBar);

        imgFavoritoAB = (ImageView) findViewById(R.id.imgFavoritoAB);
        imgFavoritoAB.setVisibility(View.INVISIBLE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.dog_footprint_filled);



        rv = (RecyclerView) findViewById(R.id.rvDetalle);
        rv.setHasFixedSize(true);

        presentador = new DetMascotaPresenter(DetalleMascota.this, DetalleMascota.this);
    }

    @Override
    public void generarLinearLayoutVertical() {

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

    }

    @Override
    public MascotasAdaptador crearAdaptador(ArrayList<Mascotas> detalles) {

        MascotasAdaptador mascotasAdaptador = new MascotasAdaptador(detalles, this);
        return mascotasAdaptador;
    }

    @Override
    public void inicializarMascotasAdaptadorRV(MascotasAdaptador mascotasAdaptador) {

        rv.setAdapter(mascotasAdaptador);

    }
}
