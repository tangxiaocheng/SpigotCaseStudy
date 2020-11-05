package com.spigot.study;

import static com.spigot.study.MainActivity.getDeviceInfoModel;

import android.util.Pair;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.spigot.study.model.HeaderModel;
import com.spigot.study.network.NetworkService;
import com.spigot.study.network.ResponseModel;
import com.spigot.study.network.RetrofitInstance;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
public class NetWorkTest {

  @Test
  public void networkServiceTest() {
    NetworkService networkService = RetrofitInstance.getInstance().getRetrofit()
        .create(NetworkService.class);
    List<Pair<String, String>> list = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      list.add(new Pair<>("key" + i, "key" + 2));
    }

    HeaderModel headerModel = new HeaderModel(getDeviceInfoModel("android Id"), list);
    networkService.postInstallInfo(headerModel).enqueue(new Callback<ResponseModel>() {
      @Override
      public void onResponse(Call<ResponseModel> call,
          Response<ResponseModel> response) {
      }

      @Override
      public void onFailure(Call<ResponseModel> call, Throwable t) {
        t.printStackTrace();
      }
    });
  }

}
