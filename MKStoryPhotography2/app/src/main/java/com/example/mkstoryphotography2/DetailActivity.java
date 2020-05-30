package com.example.mkstoryphotography2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView imageViewImgMenu;
    TextView textViewHarga;
    TextView textViewNama;
    TextView textViewKet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageViewImgMenu=findViewById(R.id.img_menu);
        textViewHarga=findViewById(R.id.tv_harga);
        textViewNama=findViewById(R.id.tv_nama);
        textViewKet=findViewById(R.id.tv_ket);

        getIncomingExtra();

    }
    private void getIncomingExtra(){
        if(getIntent().hasExtra("image_url")
                && getIntent().hasExtra("nama_url")
                && getIntent().hasExtra("harga_url")
                && getIntent().hasExtra("ket_url")){

            String imgmenu=getIntent().getStringExtra("image_url");
            String harga=getIntent().getStringExtra("harga_url");
            String nama=getIntent().getStringExtra("nama_url");
            String ket=getIntent().getStringExtra("ket_url");

            setDataActivity(imgmenu, harga, nama, ket);
        }
    }

    private void setDataActivity(String imgmenu, String harga, String nama, String ket){
        Glide
                .with(this).asBitmap()
                .load(imgmenu)
                .centerCrop()
                .into(imageViewImgMenu);
        textViewHarga.setText(harga);
        textViewNama.setText(nama);
        textViewKet.setText(ket);
    }
}
