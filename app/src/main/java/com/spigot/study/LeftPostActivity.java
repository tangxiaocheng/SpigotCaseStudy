package com.spigot.study;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.spigot.study.network.NetworkService;
import com.spigot.study.network.ResponseModel;
import com.spigot.study.network.RetrofitInstance;
import com.spigot.study.util.SpigotConstant;
import com.spigot.study.util.Util;
import java.util.HashMap;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
 *Problematic here. Compare to the RightActivity, it lack of life cycle awareness.
 * */
public class LeftPostActivity extends AppCompatActivity implements Callback<ResponseModel> {

  private String jsonString;
  private TextView displayTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post);
    displayTv = findViewById(R.id.display_tv);
    jsonString = getIntent().getStringExtra(SpigotConstant.KEY_JSON);
    post();
  }

  private void post() {
    Retrofit retrofit = RetrofitInstance.getInstance(this.getApplicationContext()).getRetrofit();
    NetworkService networkService = retrofit.create(NetworkService.class);
    HashMap<String, String> map = Util.jsonToMap(jsonString);
    networkService.postInstallInfo(map).enqueue(this);
  }

  @Override
  public void onResponse(
      @NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
    if (response.isSuccessful()) {
      displayTv.setText(Util.prettyJson(Objects.requireNonNull(response.body()).getRequest()));
    } else {
      displayTv.setText(response.message());
    }
  }

  @Override
  public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {}
}
