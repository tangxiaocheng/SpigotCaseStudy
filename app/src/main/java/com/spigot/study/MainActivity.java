package com.spigot.study;

import static com.spigot.study.util.SpigotConstant.DEVICE_INFO_MAP;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.spigot.study.adapter.DeviceInfoListAdapter;
import com.spigot.study.adapter.OnItemClickListener;
import com.spigot.study.data.DeviceInfo;
import com.spigot.study.data.DeviceInfoRepository;
import com.spigot.study.model.UrlModel;
import com.spigot.study.util.DeviceUtil;
import com.spigot.study.viewmodel.DeviceInfoListViewModel;
import java.util.HashMap;
import java.util.Map;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements OnClickListener,
    OnItemClickListener {

  private TextInputEditText urlTextInputEditText;
  private TextView deviceInfoTv;
  private String androidId;
  private Map<String, String> deviceInfoModel;
  private DeviceInfoListViewModel deviceInfoListViewModel;
  public static final String KEY_JSON = "Json";

  @SuppressLint("HardwareIds")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    deviceInfoModel = getDeviceInfoModel(androidId);
    androidId = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
    initView();

  }

  public static Map<String, String> getDeviceInfoModel(String androidId) {
    Map<String, String> map = new HashMap<>(DEVICE_INFO_MAP);
    map.put("androidID", androidId);
    return map;
  }

  private void initView() {
    setContentView(R.layout.activity_main);
    deviceInfoTv = findViewById(R.id.device_info_tv);
    urlTextInputEditText = findViewById(R.id.url_input_et);
    deviceInfoTv.setText(deviceInfoModel.toString());

    RecyclerView savedUrlsRv = findViewById(R.id.saved_urls_rv);
    findViewById(R.id.save_button).setOnClickListener(this);

    ViewModelProvider viewModelProvider = new ViewModelProvider(this);
    DeviceInfoListAdapter adapter = new DeviceInfoListAdapter(this,
        getApplicationContext());
    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
    adapter.registerAdapterDataObserver(new AdapterDataObserver() {
      @Override
      public void onItemRangeInserted(int positionStart, int itemCount) {
        super.onItemRangeInserted(positionStart, itemCount);
        manager.scrollToPosition(0);
      }
    });
    savedUrlsRv.setLayoutManager(manager);
    savedUrlsRv.setAdapter(adapter);
    deviceInfoListViewModel = viewModelProvider.get(DeviceInfoListViewModel.class);
    deviceInfoListViewModel.getLiveDataOfPagedList().observe(this, pagedList -> {
      if (pagedList != null) {
        adapter.submitList(pagedList);
      }
    });

  }


  @Override
  public void onClick(View view) {
    if (isValid(urlTextInputEditText)) {
      String originalUrl = urlTextInputEditText.getText().toString();
      UrlModel urlModel = null;
      final Map<String, String> map = new HashMap<>(deviceInfoModel);
      if (!URLUtil.isValidUrl(originalUrl)) {
        String decodeUrl = DeviceUtil.decodeUrl(originalUrl);
        if (URLUtil.isValidUrl(decodeUrl)) {
          urlModel = DeviceUtil.extractUrl(decodeUrl, map);
          insertModeLToDB(deviceInfoListViewModel.getDeviceInfoRepository(), urlModel);
        } else {
          Timber.e("Invalid url");
        }
      } else {
        urlModel = DeviceUtil.extractUrl(originalUrl, map);
        insertModeLToDB(deviceInfoListViewModel.getDeviceInfoRepository(), urlModel);
      }
    }
  }

  private void insertModeLToDB(DeviceInfoRepository deviceInfoRepository, UrlModel urlModel) {
    deviceInfoRepository.insert(new DeviceInfo(urlModel.getBaseUrl(),
        new Gson().toJson(urlModel.getParaMap()),
        System.currentTimeMillis()));
  }

  public boolean isValid(TextInputEditText urlTextInputEditText) {
    return urlTextInputEditText.getText().length() > 0;
  }

  @Override
  public void onItemClick(DeviceInfo deviceInfo) {

    Intent intent = new Intent(this, PostActivity.class);
    intent.putExtra(KEY_JSON, deviceInfo.getJson());
    startActivity(intent);

  }
}