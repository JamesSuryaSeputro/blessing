package com.example.blessing.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuildCustom {

   private static RetrofitBuildCustom instance = null;
   private Retrofit retrofit;
   private API service;
   private static final String BASE_URL = "http://192.168.1.5/blessing/blessingAPI/public/";
   //https://blessingme.herokuapp.com/public/
   //http://192.168.1.6/blessing/blessingAPI/public/
    public RetrofitBuildCustom() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(API.class);
    }

    public static RetrofitBuildCustom getInstance(){
        if (instance==null){
            synchronized (RetrofitBuildCustom.class){
                instance =  new RetrofitBuildCustom();
            }
        }
        return instance;
    }

  public API getService() {
    return service;
  }

  public Retrofit getRetrofit() {
    return retrofit;
  }
}
