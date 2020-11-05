package com.spigot.study;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.spigot.study.adapter.DeviceInfoListAdapter;
import com.spigot.study.adapter.DeviceInfoListViewModel;
import com.spigot.study.model.DeviceInfoModel;
import com.spigot.study.model.UrlModel;
import com.spigot.study.util.DeviceUtil;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private Button saveButton;
  private TextInputEditText urlTextInputEditText;
  private RecyclerView savedUrlsRv;
  private TextView deviceInfoTv;
  private String androidId;
  private DeviceInfoModel deviceInfoModel;

  @SuppressLint("HardwareIds")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initView();
    androidId = Secure
        .getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
  }

  private void initView() {
    setContentView(R.layout.activity_main);
    saveButton = findViewById(R.id.save_button);
    deviceInfoTv = findViewById(R.id.device_info_tv);
    urlTextInputEditText = findViewById(R.id.url_input_et);
    savedUrlsRv = findViewById(R.id.saved_urls_rv);
    saveButton.setOnClickListener(this);

    ViewModelProvider viewModelProvider = new ViewModelProvider(this);
    DeviceInfoListAdapter adapter = new DeviceInfoListAdapter(getApplicationContext());
    DeviceInfoListViewModel deviceInfoListViewModel = viewModelProvider
        .get(DeviceInfoListViewModel.class);
    deviceInfoListViewModel.getLiveDataOfPagedList().observe(this, pagedList -> {
      if (pagedList != null) {
        adapter.submitList(pagedList);
      }
    });
    savedUrlsRv.setAdapter(adapter);
    savedUrlsRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
  }


  @Override
  public void onClick(View view) {
    if (isValid(urlTextInputEditText)) {
      String originalUrl = urlTextInputEditText.getText().toString();
      String decodeUrl = DeviceUtil.decodeUrl(originalUrl);
      if (URLUtil.isValidUrl(decodeUrl)) {
        UrlModel urlModel = DeviceUtil.extractUrl(decodeUrl);
        if (deviceInfoModel == null) {
          deviceInfoModel = new DeviceInfoModel(androidId, Build.MANUFACTURER, Build.MODEL,
              String.valueOf(Build.VERSION.SDK_INT), Build.HOST, Build.SERIAL, Build.BRAND,
              Build.DISPLAY, DeviceUtil.getScreenMetrics());
        }
        urlTextInputEditText.setText("");
      }
    }
  }

  public boolean isValid(TextInputEditText urlTextInputEditText) {
    return urlTextInputEditText.getText().length() > 0;
  }
}