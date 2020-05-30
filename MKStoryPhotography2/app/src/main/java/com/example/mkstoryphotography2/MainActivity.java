package com.example.mkstoryphotography2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
//implements MenuAdapter.OnItemClickListener
public class MainActivity extends AppCompatActivity  {
//    public static final String EXTRA_URL = "gambar";
//    public static final String EXTRA_NAMA = "nama";
//    public static final String EXTRA_HARGA ="harga";
//    public static final String EXTRA_KET = "ket";

    private MenuAdapter menuAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Menu> menus;
    int jumdata;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        menus=new ArrayList<>();
        requestQueue= Volley.newRequestQueue(this);
        parseJSON();
    }


    private void parseJSON() {
        String url= "https://mkstoryphotography.000webhostapp.com/koneksi.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        jumdata = response.length();
                        try {
                            for (int i = 0; i < jumdata; i++) {
                                JSONObject data = response.getJSONObject(i);
                                String gambarmenu = data.getString("gambar");
                                String namamenu = data.getString("nama");
                                String hargamenu = data.getString("harga");
                                String ketmenu = data.getString("ket");

                                menus.add(new Menu(namamenu,hargamenu,gambarmenu,ketmenu));
                            }
                            menuAdapter = new MenuAdapter(MainActivity.this, menus);
                            recyclerView.setAdapter(menuAdapter);
//                            menuAdapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
    });
        requestQueue.add(request);
    }


//    @Override
//    public void OnItemClick(int potition) {
//        Intent detailIntent = new Intent(this, GoldDetailActivity.class);
//        Menu clickItem= menus.get(potition);
//
//        detailIntent.putExtra(EXTRA_URL, clickItem.getGambar());
//        detailIntent.putExtra(EXTRA_NAMA, clickItem.getNama());
//        detailIntent.putExtra(EXTRA_HARGA, clickItem.getHarga());
//        detailIntent.putExtra(EXTRA_KET, clickItem.getKet());
//
//        startActivity(detailIntent);
    }

