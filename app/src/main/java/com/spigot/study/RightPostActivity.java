package com.spigot.study;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.spigot.study.network.NetworkService;
import com.spigot.study.network.ResponseModel;
import com.spigot.study.network.RetrofitInstance;
import com.spigot.study.util.SpigotConstant;
import com.spigot.study.util.Util;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import retrofit2.Retrofit;

/*
*
* */
public class RightPostActivity extends AppCompatActivity {

  private String jsonString;
  private TextView displayTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post);
    displayTv = findViewById(R.id.display_tv);
    jsonString = getIntent().getStringExtra(SpigotConstant.KEY_JSON);
    postRxJava();
  }

  private void postRxJava() {
    Retrofit retrofit = RetrofitInstance.getInstance(this.getApplicationContext()).getRetrofit();
    NetworkService networkService = retrofit.create(NetworkService.class);
    HashMap<String, String> map = Util.jsonToMap(jsonString);
    networkService.postInstallInfoWithRxJava(map)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(RightPostActivity.this)))
        .subscribe(this::updateUI);
  }

  private void updateUI(ResponseModel responseModel) {
    displayTv.setText(String.format("Response from server:%s", responseModel.toString()));
  }

}