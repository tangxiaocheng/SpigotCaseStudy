package com.spigot.study.network;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkService {
  @POST("mobile/install")
  Call<List<ResponseModel>> postInstallInfo(@Body String user);
}