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

import com.ronicode.petagramserverendpoint.R;
import com.ronicode.petagramserverendpoint.adaptadores.MascotasAdaptador;
import com.ronicode.petagramserverendpoint.db.BaseDatos;
import com.ronicode.petagramserverendpoint.pojo.Mascotas;
import com.ronicode.petagramserverendpoint.presentador.IRecyclerViewFragmentPresenter;
import com.ronicode.petagramserverendpoint.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {

    private RecyclerView rv;
    private ArrayList<Mascotas> detalles;
    private IRecyclerViewFragmentPresenter presentador;
    BaseDatos db;
    public String mascotaFavorita;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rvMascotas);
        presentador = new RecyclerViewFragmentPresenter(this, getContext(), 0);


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
}
