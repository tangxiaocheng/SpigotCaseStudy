package com.spigot.study;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.spigot.study.network.NetworkService;
import com.spigot.study.network.RetrofitInstance;

public class PostActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post);

  }

  private void post() {
    NetworkService networkService = RetrofitInstance.getInstance(this.getApplicationContext())
        .getRetrofit()
        .create(NetworkService.class);
//    networkService.postInstallInfo(new HeaderModel(deviceInfoModel, urlModel.getPairList())).enqueue();
  }
}