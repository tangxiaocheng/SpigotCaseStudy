package com.spigot.study.network;

import retrofit2.Retrofit;

public class RetrofitInstance {

  private volatile static RetrofitInstance instance;
  public static final String BASE_URL = "https://casestudy.alltheapps.org";
  private final Retrofit retrofit;

  private RetrofitInstance() {
    retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
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
