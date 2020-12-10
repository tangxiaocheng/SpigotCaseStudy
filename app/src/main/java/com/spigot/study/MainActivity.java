package com.spigot.study;

import static com.spigot.study.adapter.DeviceInfoListAdapter.TYPE_LEFT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.spigot.study.adapter.DeviceInfoListAdapter;
import com.spigot.study.adapter.OnItemClickListener;
import com.spigot.study.model.UrlModel;
import com.spigot.study.room.DeviceInfo;
import com.spigot.study.room.DeviceInfoRepository;
import com.spigot.study.util.SpigotConstant;
import com.spigot.study.util.Util;
import com.spigot.study.viewmodel.DeviceInfoListViewModel;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity
    implements OnClickListener, OnItemClickListener {

  private TextInputEditText urlTextInputEditText;
  private Map<String, String> deviceInfoMap;
  private DeviceInfoListViewModel deviceInfoListViewModel;
  private TextView deviceInfoTv;
  private RecyclerView savedUrlsRv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    deviceInfoMap =
        Util.getDeviceInfoModel(Util.getAndroidID(getApplicationContext().getContentResolver()));
    findView();
    bindData();
  }

  private void findView() {
    setContentView(R.layout.activity_main);
    deviceInfoTv = findViewById(R.id.device_info_tv);
    urlTextInputEditText = findViewById(R.id.url_input_et);
    savedUrlsRv = findViewById(R.id.saved_urls_rv);
    findViewById(R.id.parse_button).setOnClickListener(this);
  }

  private void bindData() {
    deviceInfoTv.setText(String.format("Device Info:\n%s", deviceInfoMap.toString()));
    ViewModelProvider viewModelProvider = new ViewModelProvider(this);
    DeviceInfoListAdapter adapter = new DeviceInfoListAdapter(this, getApplicationContext());
    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
    adapter.registerAdapterDataObserver(
        new AdapterDataObserver() {
          @Override
          public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            manager.scrollToPosition(0);
          }
        });
    savedUrlsRv.setLayoutManager(manager);
    savedUrlsRv.setAdapter(adapter);
    deviceInfoListViewModel = viewModelProvider.get(DeviceInfoListViewModel.class);
    deviceInfoListViewModel
        .getLiveDataOfPagedList()
        .observe(
            this,
            pagedList -> {
              if (pagedList != null) {
                adapter.submitList(pagedList);
              }
            });
  }

  @Override
  public void onClick(View view) {
    if (Objects.requireNonNull(urlTextInputEditText.getText()).length() > 0) {
      decodeUrl();
    }
  }

  private void decodeUrl() {
    String originalUrl = Objects.requireNonNull(urlTextInputEditText.getText()).toString();
    UrlModel urlModel;
    final Map<String, String> map = new HashMap<>(deviceInfoMap);
    if (!URLUtil.isValidUrl(originalUrl)) {
      String decodeUrl = Util.decodeUrl(originalUrl);
      if (URLUtil.isValidUrl(decodeUrl)) {
        urlModel = Util.extractUrl(decodeUrl, map);
        insertParasToDB(deviceInfoListViewModel.getDeviceInfoRepository(), urlModel);
      } else {
        Timber.e("Invalid url");
        Toast.makeText(this, "Invalid url", Toast.LENGTH_SHORT).show();
      }
    } else {
      // In case the URL might be a valid url.
      urlModel = Util.extractUrl(originalUrl, map);
      insertParasToDB(deviceInfoListViewModel.getDeviceInfoRepository(), urlModel);
    }
  }

  private void insertParasToDB(DeviceInfoRepository deviceInfoRepository, UrlModel urlModel) {
    deviceInfoRepository.insert(
        new DeviceInfo(
            urlModel.getBaseUrl(),
            new Gson().toJson(urlModel.getParaMap()),
            System.currentTimeMillis()));
  }

  @Override
  public void onItemClick(DeviceInfo deviceInfo) {
    Intent intent;
    if (deviceInfo.getViewType() == TYPE_LEFT) {
      intent = new Intent(this, LeftPostActivity.class);
    } else {
      intent = new Intent(this, RightPostActivity.class);
    }
    intent.putExtra(SpigotConstant.KEY_JSON, deviceInfo.getJson());
    startActivity(intent);
  }
}
