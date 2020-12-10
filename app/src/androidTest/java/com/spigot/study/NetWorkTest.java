package com.spigot.study;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.spigot.study.network.NetworkService;
import com.spigot.study.network.ResponseModel;
import com.spigot.study.network.RetrofitInstance;
import com.spigot.study.util.SpigotConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
public class NetWorkTest {

  @Test
  public void networkServiceTest() {
    Context context = ApplicationProvider.getApplicationContext();
    NetworkService networkService =
        RetrofitInstance.getInstance(context).getRetrofit().create(NetworkService.class);
    Map<String, String> map = new HashMap<>(SpigotConstant.DEVICE_INFO_MAP);
    map.put("testKey", "testValue");
    Call<ResponseModel> responseModelCall = networkService.postInstallInfo(map);
    responseModelCall.enqueue(
        new Callback<ResponseModel>() {
          @Override
          public void onResponse(
              @NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
            Assert.assertEquals(
                Objects.requireNonNull(response.body()).getRequest().toString(), map.toString());
          }

          @Override
          public void onFailure(Call<ResponseModel> call, Throwable t) {}
        });
  }
}
