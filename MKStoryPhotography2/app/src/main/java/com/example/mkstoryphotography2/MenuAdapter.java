package com.example.mkstoryphotography2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    private ArrayList<Menu> menus;

    public MenuAdapter(Context mcontext, ArrayList<Menu> menupaket){
        context=mcontext;
        menus=menupaket;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_menu,parent,false);

        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, final int position) {
        Menu menubaru=menus.get(position);
        final String gambarbaru=menubaru.getGambar();
        final String harga=menubaru.getHarga();
        final String nama=menubaru.getNama();
        final String ket=menubaru.getKet();

        holder.tvnamadata.setText(nama);
        holder.tvhargadata.setText(harga);
        holder.tvketdata.setText(ket);
        Glide
                .with(context)
                .load(gambarbaru)
                .centerCrop()
                .into(holder.imdata);

        holder.btndata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Toast.makeText(context, nama,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("image_url",gambarbaru);
                intent.putExtra("nama_url", nama);
                intent.putExtra("harga_url", harga);
                intent.putExtra("ket_url", ket);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        public ImageView imdata;
        public TextView tvhargadata;
        public TextView tvnamadata;
        public TextView tvketdata;
        public Button btndata;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imdata=itemView.findViewById(R.id.img_menu);
            tvhargadata=itemView.findViewById(R.id.tv_harga);
            tvnamadata=itemView.findViewById(R.id.tv_nama);
            tvketdata=itemView.findViewById(R.id.tv_ket);
            btndata=itemView.findViewById(R.id.btn_pesan);
        }
    }
}
