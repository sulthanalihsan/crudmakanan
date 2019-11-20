package com.catatanasad.crudmakanan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.catatanasad.crudmakanan.R;
import com.catatanasad.crudmakanan.UpdateDeleteActivity;
import com.catatanasad.crudmakanan.model.DataItem;

import java.util.List;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.ViewHolder> {

    List<DataItem> dataItems;
    Context context;

    public MakananAdapter(List<DataItem> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MakananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_makanan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakananAdapter.ViewHolder holder, int position) {

        final String nama = dataItems.get(position).getMenuNama();
        final String harga = dataItems.get(position).getMenuHarga();
        final String url = dataItems.get(position).getMenuGambar();
        final String id = dataItems.get(position).getMenuId();

        holder.txtNama.setText(nama);
        holder.txtHarga.setText(harga);
        Glide.with(context).load(url).into(holder.imgMakanan);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // todo kirim data dengan parcelable
                DataItem dataItem = new DataItem();
                dataItem.setMenuGambar(url);
                dataItem.setMenuHarga(harga);
                dataItem.setMenuNama(nama);
                dataItem.setMenuId(id);

                // todo eksekusi
                Intent intent = new Intent(context, UpdateDeleteActivity.class);
                intent.putExtra(UpdateDeleteActivity.KEY_DATA,dataItem);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMakanan;
        TextView txtNama, txtHarga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMakanan = itemView.findViewById(R.id.list_gambar);
            txtHarga = itemView.findViewById(R.id.list_harga);
            txtNama = itemView.findViewById(R.id.list_nama);
        }
    }
}