package com.spigot.study.network;

import com.spigot.study.model.HeaderModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkService {

  @POST("mobile/install")
  Call<ResponseModel> postInstallInfo(@Body HeaderModel user);
}