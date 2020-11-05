package com.spigot.study.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

  private volatile static RetrofitInstance instance;
  public static final String BASE_URL = "https://casestudy.alltheapps.org/";
  private final Retrofit retrofit;

  private RetrofitInstance() {
    retrofit = new Retrofit.Builder().client(new OkHttpClient())
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static RetrofitInstance getInstance() {
    if (instance == null) {
      synchronized (RetrofitInstance.class) {
        if (instance == null) {
          instance = new RetrofitInstance();
        }
      }
    }
    return instance;
  }

  public Retrofit getRetrofit() {
    return retrofit;
  }

}
