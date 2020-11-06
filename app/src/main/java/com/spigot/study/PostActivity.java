package com.spigot.study;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spigot.study.network.NetworkService;
import com.spigot.study.network.ResponseModel;
import com.spigot.study.network.RetrofitInstance;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostActivity extends AppCompatActivity implements Callback<ResponseModel> {

  private String json;
  private TextView displayTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post);
    displayTv = findViewById(R.id.display_tv);
    json = getIntent().getStringExtra(MainActivity.KEY_JSON);
    // TODO deal with lifecycle case since we have a network request in this activity
//    post();
    postRxJava();
  }

  private void postRxJava() {
    Retrofit retrofit = RetrofitInstance.getInstance(this.getApplicationContext()).getRetrofit();
    NetworkService networkService = retrofit.create(NetworkService.class);
    Gson gson = new Gson();
    Type type = new TypeToken<HashMap<String, String>>() {
    }.getType();
    HashMap<String, String> map = gson.fromJson(json, type);
    networkService.postInstallInfoWithRxJava(map)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(PostActivity.this)))
        .subscribe(this::updateUI);
  }

  private void updateUI(ResponseModel responseModel) {
    displayTv.setText(responseModel.toString());
  }

  private void post() {
    Retrofit retrofit = RetrofitInstance.getInstance(this.getApplicationContext()).getRetrofit();
    NetworkService networkService = retrofit.create(NetworkService.class);
    Gson gson = new Gson();
    Type type = new TypeToken<HashMap<String, String>>() {
    }.getType();
    HashMap<String, String> map = gson.fromJson(json, type);
    networkService.postInstallInfo(map).enqueue(this);
  }

  @Override
  public void onResponse(@NonNull Call<ResponseModel> call,
      @NonNull Response<ResponseModel> response) {
    if (response.isSuccessful()) {
      displayTv.setText(response.body().getRequest().toString());
    } else {
      displayTv.setText(response.message());
    }
  }

  @Override
  public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {

  }
}