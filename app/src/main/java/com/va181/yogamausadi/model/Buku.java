package com.va181.yogamausadi.model;

import com.google.gson.annotations.SerializedName;

public class Buku {
    @SerializedName("id_buku")
    private int id_buku;
    @SerializedName("nama_buku")
    private String nama_buku;
    @SerializedName("pengarang_buku")
    private String pengarang_buku;
    @SerializedName("penerbit_buku")
    private String penerbit_buku;
    @SerializedName("tahun_terbit")
    private String tahun_terbit;
    @SerializedName("deskripsi")
    private String deskripsi;

public Buku(int id_buku, String nama_buku, String pengarang_buku, String penerbit_buku, String tahun_terbit, String deskripsi){
    this.id_buku = id_buku;
    this.nama_buku = nama_buku;
    this.pengarang_buku = pengarang_buku;
    this.penerbit_buku = penerbit_buku;
    this.tahun_terbit = tahun_terbit;
    this.deskripsi = deskripsi;
}
public int getId_buku(){ return id_buku; }
public void setId_buku(int id_buku){this.id_buku = id_buku;}

public String getNama_buku(){return nama_buku;}
public void setNama_buku(String nama_buku){this.nama_buku = nama_buku;}

public String getPengarang_buku(){return pengarang_buku;}
public void  setPengarang_buku(String pengarang_buku){this.pengarang_buku = pengarang_buku;}

public String getPenerbit_buku(){return penerbit_buku;}
public void setPenerbit_buku(String pengarang_buku){this.pengarang_buku = pengarang_buku;}

public String getTahun_terbit(){return tahun_terbit;}
public void  setTahun_terbit(String tahun_terbit){this.tahun_terbit = tahun_terbit;}

public String getDeskripsi(){return deskripsi;}
public void setDeskripsi(String deskripsi){this.deskripsi = deskripsi;}
}
