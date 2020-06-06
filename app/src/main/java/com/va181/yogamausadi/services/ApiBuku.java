package com.va181.yogamausadi.services;

import com.va181.yogamausadi.model.ResponseBuku;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBuku {

        @FormUrlEncoded
        @POST("tambah.php")
        Call<ResponseBuku>tambahBuku(
            @Field("nama_buku") String nama_buku,
            @Field("pengarang_buku") String pengarang_buku,
            @Field("penerbit_buku") String penerbit_buku,
            @Field("tahun_terbit") String tahun_terbit,
            @Field("deskripsi") String deskripsi
        );

        @FormUrlEncoded
        @POST("edit.php")
        Call<ResponseBuku>editBuku(
                @Field("id_buku") String id_buku,
                @Field("nama_buku") String nama_buku,
                @Field("pengarang_buku") String pengarang_buku,
                @Field("penerbit_buku") String penerbit_buku,
                @Field("tahun_terbit") String tahun_terbit,
                @Field("deskripsi") String deskripsi
        );

        @FormUrlEncoded
        @POST("delete.php")
        Call<ResponseBuku>hapusData(
            @Field("id_buku") String id_buku
        );

        @FormUrlEncoded
        @POST("search.php")
        Call<ResponseBuku>cariBuku(
            @Field("keyword") String keyword
        );

        @GET("getDataBuku.php")
        Call<ResponseBuku>getData();
}
