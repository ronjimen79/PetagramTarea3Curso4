package com.ronicode.petagramserverendpoint.adaptadores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ronicode.petagramserverendpoint.R;
import com.ronicode.petagramserverendpoint.db.BaseDatos;
import com.ronicode.petagramserverendpoint.pojo.Mascotas;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Roni on 10/06/2017.
 */

public class MascotasAdaptador extends RecyclerView.Adapter<MascotasAdaptador.mascotaViewHolder> {

    private List<Mascotas> detalles;
    private int sum;
    BaseDatos db;
    Activity activity;

    public static class mascotaViewHolder extends RecyclerView.ViewHolder{

        public ImageView foto;
        public TextView nombre;
        public TextView tvFavoritoCV;
        //public ImageView imgFavoritoCV;

        public mascotaViewHolder(View itemView) {
            super(itemView);

            foto            = (ImageView) itemView.findViewById(R.id.imgFoto);
            nombre          = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvFavoritoCV    = (TextView) itemView.findViewById(R.id.tvFavoritoCV);
            //imgFavoritoCV   = (ImageView) itemView.findViewById(R.id.imgFavoritoCV);
        }
    }

    public MascotasAdaptador(List<Mascotas> detalles, Activity activity){

        this.detalles = detalles;
        this.activity = activity;
    }

    @Override
    public mascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_mascotas, parent, false);
        return new mascotaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final mascotaViewHolder holder, final int position) {

        //holder.foto.setImageResource(detalles.get(position).getFoto());
        //holder.nombre.setText(detalles.get(position).getNombre());

        final Mascotas mascotas = detalles.get(position);

        Picasso.with(activity)
                .load(mascotas.getUrlFoto())
                .placeholder(R.drawable.perro_6_shaggy)
                .into(holder.foto);

        holder.tvFavoritoCV.setText(String.valueOf(detalles.get(position).getLikes()));

        /*holder.imgFavoritoCV.setTag(holder);

        holder.imgFavoritoCV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                db = new BaseDatos(view.getContext());
                mascotaViewHolder sum = (mascotaViewHolder) view.getTag();
                sum.tvFavoritoCV.setText(String.valueOf(1 + Integer.parseInt(sum.tvFavoritoCV.getText().toString())));
                detalles.get(position).setTvFavoritoCV(Integer.parseInt(sum.tvFavoritoCV.getText().toString()));
                db.actualizarFavorito(detalles.get(position));
            }
        });*/

    }

    @Override
    public int getItemCount() {

        return detalles.size();
    }
}
