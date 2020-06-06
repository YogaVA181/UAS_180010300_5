package com.va181.yogamausadi.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit;
    public static final String BASE_URL = "http://100.68.8.180/buku/";
    public static Retrofit getRetrofit(){
        if(retrofit==null){
            try {
                   retrofit=new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                }catch (Exception er){
                    er.printStackTrace();
                }
                }
                    return retrofit;
                }
}
