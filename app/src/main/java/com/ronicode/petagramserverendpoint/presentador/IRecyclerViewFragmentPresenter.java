package com.ronicode.petagramserverendpoint.presentador;

/**
 * Created by Roni on 10/06/2017.
 */

public interface IRecyclerViewFragmentPresenter {

    public void inicializarListaMascota();
    public void mostrarMascotasRV();
    public void iniciar();
    public void obtenerFollows();
    public void obtenerMediaRecentFollows();
    public void crearFavorito();
}
