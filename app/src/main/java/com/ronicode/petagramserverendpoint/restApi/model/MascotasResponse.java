package com.ronicode.petagramserverendpoint.restApi.model;

import com.ronicode.petagramserverendpoint.pojo.Mascotas;

import java.util.ArrayList;

/**
 * Created by Roni on 10/06/2017.
 */

public class MascotasResponse {

    ArrayList<Mascotas> mascotas;

    public ArrayList<Mascotas> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascotas> mascotas) {
        this.mascotas = mascotas;
    }
}
