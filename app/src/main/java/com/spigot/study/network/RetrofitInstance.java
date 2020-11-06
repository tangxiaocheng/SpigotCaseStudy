package com.spigot.study.network;

import android.content.Context;
import com.chuckerteam.chucker.api.ChuckerCollector;
import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.chuckerteam.chucker.api.ChuckerInterceptor.Builder;
import com.spigot.study.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

  private volatile static RetrofitInstance instance;
  public static final String BASE_URL = "https://casestudy.alltheapps.org/";
  private final Retrofit retrofit;

  private RetrofitInstance(Context context) {

    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    if (BuildConfig.DEBUG) {
      ChuckerCollector chuckerCollector = new ChuckerCollector(context);
      ChuckerInterceptor chuckerInterceptor = new Builder(context).alwaysReadResponseBody(true)
          .collector(chuckerCollector).build();
      builder.addInterceptor(chuckerInterceptor);
    }

    OkHttpClient client = builder.build();

    retrofit = new Retrofit.Builder().client(client)
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
