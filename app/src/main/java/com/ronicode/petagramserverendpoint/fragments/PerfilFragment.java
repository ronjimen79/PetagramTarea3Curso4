package com.ronicode.petagramserverendpoint.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ronicode.petagramserverendpoint.R;
import com.ronicode.petagramserverendpoint.adaptadores.MascotasAdaptador;
import com.ronicode.petagramserverendpoint.db.BaseDatos;
import com.ronicode.petagramserverendpoint.pojo.Mascotas;
import com.ronicode.petagramserverendpoint.presentador.IPerfilFragmentPresenter;
import com.ronicode.petagramserverendpoint.presentador.PerfilFragmentPresenter;
import com.ronicode.petagramserverendpoint.restApi.deserializador.FotoPerfilDeserializador;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PerfilFragment extends Fragment implements IPerfilFragmentView {

    private RecyclerView rv;
    private ArrayList<Mascotas> detalles;
    private IPerfilFragmentPresenter presentador;
    public String mascotaFavorita;
    private static TextView tvPerfil;
    public ImageView civPerfil;

    public static String nombre;
    BaseDatos db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        presentador = new PerfilFragmentPresenter(this, getContext());
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rvPerfil);
        tvPerfil = (TextView) view.findViewById(R.id.tvPerfil);
        tvPerfil.setText(String.valueOf(FotoPerfilDeserializador.nombreCompleto));

        civPerfil = (ImageView) view.findViewById(R.id.civPerfil);

        Picasso.with(getActivity())
                .load(FotoPerfilDeserializador.urlFoto)
                .placeholder(R.drawable.perro_6_shaggy)
                .into(civPerfil);

        /*inicializarListaMascota();
        inicializarMascotasAdaptador();*/

        return view;
    }

    @Override
    public void generarLinearLayoutVertical() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rv.setLayoutManager(llm);

    }

    @Override
    public void generarGridLayout() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rv.setLayoutManager(gridLayoutManager);

    }

    @Override
    public MascotasAdaptador crearAdaptador(ArrayList<Mascotas> detalles) {

        MascotasAdaptador mascotasAdaptador = new MascotasAdaptador(detalles, getActivity());
        return mascotasAdaptador;
    }

    @Override
    public void inicializarMascotasAdaptadorRV(MascotasAdaptador mascotasAdaptador) {

        rv.setAdapter(mascotasAdaptador);

    }

     /*public void inicializarMascotasAdaptador(){

        MascotasAdaptador adaptador = new MascotasAdaptador(detalles);
        rv.setAdapter(adaptador);
    }

    public void inicializarListaMascota(){

        int contador = 1;
        detalles = new ArrayList<>();

        for (int i = 0; i<=4; i++){
            detalles.add(new Mascotas("Shaggy", R.drawable.perro_6_shaggy, contador));
            contador*=2;
        }

    }*/
}
