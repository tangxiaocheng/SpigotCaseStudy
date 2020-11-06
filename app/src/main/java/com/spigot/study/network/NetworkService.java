package com.spigot.study.network;

import io.reactivex.Single;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkService {

  @POST("mobile/install")
  Call<ResponseModel> postInstallInfo(@Body Map<String, String> map);

  @POST("mobile/install")
  Single<ResponseModel> postInstallInfoWithRxJava(@Body Map<String, String> map);
}