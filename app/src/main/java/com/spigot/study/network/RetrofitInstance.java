package com.spigot.study.network;

import android.content.Context;
import com.chuckerteam.chucker.api.ChuckerCollector;
import com.chuckerteam.chucker.api.ChuckerInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

  private volatile static RetrofitInstance instance;
  public static final String BASE_URL = "https://casestudy.alltheapps.org/";
  private final Retrofit retrofit;

  private RetrofitInstance(Context context) {

    ChuckerCollector chuckerCollector = new ChuckerCollector(context);
    OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(new ChuckerInterceptor.Builder(context).alwaysReadResponseBody(true)
            .collector(chuckerCollector).build())
        .build();

    retrofit = new Retrofit.Builder().client(client)
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static RetrofitInstance getInstance(Context context) {
    if (instance == null) {
      synchronized (RetrofitInstance.class) {
        if (instance == null) {
          instance = new RetrofitInstance(context);
        }
      }
    }
    return instance;
  }

  public Retrofit getRetrofit() {
    return retrofit;
  }

}
