package com.spigot.study;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.spigot.study.network.NetworkService;
import com.spigot.study.network.RetrofitInstance;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NetWorkTest {

  @Test
  public void networkServiceTest() {
    Context context = ApplicationProvider.getApplicationContext();
    NetworkService networkService = RetrofitInstance.getInstance(context).getRetrofit()
        .create(NetworkService.class);
  }

}
