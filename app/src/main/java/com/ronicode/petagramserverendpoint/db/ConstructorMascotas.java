package com.ronicode.petagramserverendpoint.db;

import android.content.ContentValues;
import android.content.Context;

import com.ronicode.petagramserverendpoint.R;
import com.ronicode.petagramserverendpoint.pojo.Mascotas;

import java.util.ArrayList;

/**
 * Created by Roni on 10/06/2017.
 */

public class ConstructorMascotas {

    private Context context;
    public ConstructorMascotas(Context context){
        this.context = context;
    }

    public ArrayList<Mascotas> obtenerDatos(){

        BaseDatos db = new BaseDatos(context);
        cargarIniciarLMascotas(db);
        return db.obtenerTodosLasMascotas();

    }

    public void cargarIniciarLMascotas (BaseDatos db){

        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_NOMBRE, "Shaggy");
        contentValues.put(ConstantesBaseDatos.TABLE_NUMERO_FAVORITOS, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro_6_shaggy);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_NOMBRE, "Winnie");
        contentValues.put(ConstantesBaseDatos.TABLE_NUMERO_FAVORITOS, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro_1_winnie);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_NOMBRE, "Leto");
        contentValues.put(ConstantesBaseDatos.TABLE_NUMERO_FAVORITOS, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro_2_leto);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_NOMBRE, "Dalton");
        contentValues.put(ConstantesBaseDatos.TABLE_NUMERO_FAVORITOS, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro_3_dalton);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_NOMBRE, "Truman");
        contentValues.put(ConstantesBaseDatos.TABLE_NUMERO_FAVORITOS, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro_5_truman);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_NOMBRE, "Minnie");
        contentValues.put(ConstantesBaseDatos.TABLE_NUMERO_FAVORITOS, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro_4_minnie);
        db.insertarMascotas(contentValues);

    }
}
