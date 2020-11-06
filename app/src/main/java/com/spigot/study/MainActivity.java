package com.spigot.study;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
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
import com.spigot.study.adapter.DeviceInfoListAdapter;
import com.spigot.study.adapter.OnItemClickListener;
import com.spigot.study.data.DeviceInfo;
import com.spigot.study.data.DeviceInfoRepository;
import com.spigot.study.model.DeviceInfoModel;
import com.spigot.study.model.UrlModel;
import com.spigot.study.util.DeviceUtil;
import com.spigot.study.viewmodel.DeviceInfoListViewModel;

public class MainActivity extends AppCompatActivity implements OnClickListener,
    OnItemClickListener {

  private TextInputEditText urlTextInputEditText;
  private TextView deviceInfoTv;
  private String androidId;
  private DeviceInfoModel deviceInfoModel;
  private DeviceInfoListViewModel deviceInfoListViewModel;

  @SuppressLint("HardwareIds")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initView();
    androidId = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
    deviceInfoModel = getDeviceInfoModel(androidId);
  }

  public static DeviceInfoModel getDeviceInfoModel(String androidId) {
    return new DeviceInfoModel(androidId, Build.MANUFACTURER, Build.MODEL,
        String.valueOf(Build.VERSION.SDK_INT), Build.HOST, Build.SERIAL, Build.BRAND,
        Build.DISPLAY, DeviceUtil.getScreenMetrics());
  }

  private void initView() {
    setContentView(R.layout.activity_main);
    deviceInfoTv = findViewById(R.id.device_info_tv);
    urlTextInputEditText = findViewById(R.id.url_input_et);
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

      String decodeUrl = DeviceUtil.decodeUrl(originalUrl);
      if (URLUtil.isValidUrl(decodeUrl)) {
        UrlModel urlModel = DeviceUtil.extractUrl(decodeUrl);
        insertModeLToDB(deviceInfoListViewModel.getDeviceInfoRepository(), urlModel,
            deviceInfoModel);
//        urlTextInputEditText.setText("");

      }
    }
  }

  private void insertModeLToDB(DeviceInfoRepository deviceInfoRepository, UrlModel urlModel,
      DeviceInfoModel deviceInfoModel) {
    deviceInfoRepository.insert(new DeviceInfo(urlModel.getBaseUrl(),
        urlModel.getPairList().toString() + deviceInfoModel.toString(),
        System.currentTimeMillis()));
  }

  public boolean isValid(TextInputEditText urlTextInputEditText) {
    return urlTextInputEditText.getText().length() > 0;
  }

  @Override
  public void onItemClick(DeviceInfo deviceInfo) {

    Intent intent = new Intent(this, PostActivity.class);
//    intent.putExtra("Data",  "deviceInfo_TODo)");
    //TODO
    startActivity(intent);

  }
}