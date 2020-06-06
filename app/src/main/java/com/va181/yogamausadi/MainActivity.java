package com.va181.yogamausadi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.va181.yogamausadi.adapter.BukuAdapter;
import com.va181.yogamausadi.model.Buku;
import com.va181.yogamausadi.model.ResponseBuku;
import com.va181.yogamausadi.services.ApiBuku;
import com.va181.yogamausadi.services.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabBuku;
    private RecyclerView rvTampilBuku;
    private List<Buku> dataBuku;
    private BukuAdapter adapter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabBuku = findViewById(R.id.fab_tambah_buku);

        fabBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bukaInputBuku = new Intent(getApplicationContext(), InputBukuActivity.class);
                bukaInputBuku.putExtra("OPERASI", "insert");
                startActivity(bukaInputBuku);
            }
        });
        rvTampilBuku = findViewById(R.id.rv_tampil_buku);
    }



    private void tampilBuku() {
        try {
            ApiBuku api = ApiClient.getRetrofit().create(ApiBuku.class);
            Call<ResponseBuku> call = api.getData();
            call.enqueue(new Callback<ResponseBuku>() {
                @Override
                public void onResponse(Call<ResponseBuku> call, Response<ResponseBuku> response) {
                    String value = response.body().getValue();
                    if (value.equals("1")) {
                        dataBuku = response.body().getResult();
                        adapter = new BukuAdapter(dataBuku, getApplicationContext());
                        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
                        rvTampilBuku.setLayoutManager(manager);
                        rvTampilBuku.setAdapter(adapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBuku> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "failure:" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception er) {
            Toast.makeText(this, "Error : " + er.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        tampilBuku();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        final MenuItem menuCari = menu.findItem(R.id.item_menu_cari);
        final androidx.appcompat.widget.SearchView cariBuku = (SearchView) menuCari.getActionView();
       cariBuku.setQueryHint("Pencarian Buku");
       cariBuku.setIconified(true);
       cariBuku.setOnQueryTextListener(new SearchView.OnQueryTextListener() { //dalam kurung ctrl + spasi
            @Override
            public boolean onQueryTextSubmit(String query) {
                cariBuku(query);
                return true;
            }

            @Override
         public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void cariBuku(String keyword){
        try{
           ApiBuku api = ApiClient.getRetrofit().create(ApiBuku.class);
            Call<ResponseBuku> call = api.cariBuku(keyword);
            call.enqueue(new Callback<ResponseBuku>() {
                @Override
                public void onResponse(Call<ResponseBuku> call, Response<ResponseBuku> response) {
                    String value = response.body().getValue();
                    if(value.equals("1")){
                        dataBuku = response.body().getResult();
                        adapter = new BukuAdapter(dataBuku,getApplicationContext());
                        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
                        rvTampilBuku.setLayoutManager(manager);
                        rvTampilBuku.setAdapter(adapter);
                    }else{
                        Toast.makeText(MainActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                    }
               }

                @Override
                public void onFailure(Call<ResponseBuku> call, Throwable t) {
                   Toast.makeText(MainActivity.this, "failure:"+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception er){
            Toast.makeText(this, "Error : "+er.getMessage() , Toast.LENGTH_LONG).show();
        }
    }
}

