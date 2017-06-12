package com.ronicode.petagramserverendpoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class AcercaDe extends AppCompatActivity {

    ImageView imgFavoritoAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        imgFavoritoAB = (ImageView) findViewById(R.id.imgFavoritoAB);
        imgFavoritoAB.setVisibility(View.INVISIBLE);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.dog_footprint_filled);

    }
}
