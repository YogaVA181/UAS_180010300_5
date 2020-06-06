package com.va181.yogamausadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.va181.yogamausadi.model.ResponseBuku;
import com.va181.yogamausadi.services.ApiBuku;
import com.va181.yogamausadi.services.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputBukuActivity extends AppCompatActivity {

    private EditText editNama, editPengarang, editPenerbit, editTahun, editDeskripsi;
    private Button btnSimpan;

    private Integer id_buku = 0;
    private Boolean operasiPembeharuan = false;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_buku);
        editNama = findViewById(R.id.edit_nama);
        editPengarang = findViewById(R.id.edit_pengarang);
        editPenerbit = findViewById(R.id.edit_penerbit);
        editTahun = findViewById(R.id.edit_tahun);
        editDeskripsi = findViewById(R.id.edit_deskripsi);
        btnSimpan = findViewById(R.id.btn_simpan);


        progressDialog = new ProgressDialog(this);
        btnSimpan.setOnClickListener(new View.OnClickListener() { //untuk menyimpan data
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });

        Intent terimaData = getIntent();
        Bundle data = terimaData.getExtras();
        if (data.getString("OPERASI").equals("insert")) {
            operasiPembeharuan = false;
        } else {
            operasiPembeharuan = true;
            id_buku = data.getInt("ID");
            editNama.setText(data.getString("NAMA"));
            editPengarang.setText(data.getString("PENGARANG"));
            editPenerbit.setText(data.getString("PENERBIT"));
            editTahun.setText(data.getString("TAHUN"));
            editDeskripsi.setText(data.getString("DESKRIPSI"));
        }
    }





    private void simpanData(){ //method untuk menyimpan data nasabah / mengambil inputan dari layout
        progressDialog.setMessage("Menyimpan Data");
        progressDialog.show();

        String nama_buku          = editNama.getText().toString();
        String pengarang_buku     = editPengarang.getText().toString();
        String penerbit_buku      = editPenerbit.getText().toString();
        String tahun_terbit       = editTahun.getText().toString();
        String deskripsi          = editDeskripsi.getText().toString();

        if(!(nama_buku.equals("")&&pengarang_buku.equals("")&&penerbit_buku.equals("")&&tahun_terbit.equals("")&&deskripsi.equals(""))){
            ApiBuku api = ApiClient.getRetrofit().create(ApiBuku.class);//mengkoneksikan ke file php melalui api client dan api client menghubungkn ke lokasi
            Call<ResponseBuku> call;
            if(operasiPembeharuan == false){
                call = api.tambahBuku(nama_buku,pengarang_buku,penerbit_buku,tahun_terbit,deskripsi);//menambah data   FUNGSI CALL ADALAH MENGAKSES SEBUAH API DI PHP
            }else {
                call = api.editBuku(String.valueOf(id_buku),nama_buku,pengarang_buku,penerbit_buku,tahun_terbit,deskripsi); //edit data
            }
            call.enqueue(new Callback<ResponseBuku>() {
                @Override
                public void onResponse(Call<ResponseBuku> call, Response<ResponseBuku> response) {
                    progressDialog.dismiss();
                    String value = response.body().getValue();
                    String mesage = response.body().getMessage();

                    if (value.equals("1")){
                        Toast.makeText(InputBukuActivity.this, "SUKSES: "+mesage, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(InputBukuActivity.this, "GAGAL:"+mesage, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBuku> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(InputBukuActivity.this, "GAGAL TERHUBUNG KE SERVER : "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else {
            Toast.makeText(this, "Silahkan lengkapi data yang diperlukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_input,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuHapus = menu.findItem(R.id.item_menu_delete);
        if(operasiPembeharuan == true){
            menuHapus.setEnabled(true);
            menuHapus.getIcon().setAlpha(255);

        }else {
            menuHapus.setEnabled(false);
            menuHapus.getIcon().setAlpha(128);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Integer idMenu = item.getItemId();
        if(idMenu==R.id.item_menu_delete) {
            deleteData();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


    private void deleteData() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Hapus Data");
        builder.setMessage("Apakah anda yakin ingin menghapus data?")
                .setCancelable(false)
                .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        progressDialog.setMessage("Deleting...");
                        progressDialog.show();
                        ApiBuku api = ApiClient.getRetrofit().create(ApiBuku.class);
                        Call<ResponseBuku> call = api.hapusData(String.valueOf(id_buku));
                        call.enqueue(new Callback<ResponseBuku>() {
                            @Override
                            public void onResponse(Call<ResponseBuku> call, Response<ResponseBuku> response) {
                                String value = response.body().getValue();
                                String message = response.body().getMessage();
                                progressDialog.dismiss();

                                if(value.equals("1")) {
                                    Toast.makeText(InputBukuActivity.this, "SUKSES: " + message, Toast.LENGTH_LONG).show();
                                } else{
                                    Toast.makeText(InputBukuActivity.this, "GAGAL: " + message, Toast.LENGTH_LONG).show();
                                }

                                finish();
                            }

                            @Override
                            public void onFailure(Call<ResponseBuku> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(InputBukuActivity.this, "Gagal menghubungi server...", Toast.LENGTH_SHORT).show();
                                t.printStackTrace();
                                Log.d("Detele Data Error", t.toString());
                            }
                        });
                    }
                }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}

