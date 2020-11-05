package com.spigot.study;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.spigot.study.model.UrlModel;
import com.spigot.study.util.DeviceUtil;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private Button saveButton;
  private TextInputEditText urlTextInputEditText;
  private RecyclerView savedUrlsRv;
  private TextView deviceInfoTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    saveButton = findViewById(R.id.save_button);
    deviceInfoTv = findViewById(R.id.device_info_tv);
    urlTextInputEditText = findViewById(R.id.url_input_et);
    savedUrlsRv = findViewById(R.id.saved_urls_rv);
    saveButton.setOnClickListener(this);

  }

  @Override
  public void onClick(View view) {
    if (isValid(urlTextInputEditText)) {

      String originalUrl = urlTextInputEditText.getText().toString();
      String decodeUrl = DeviceUtil.decodeUrl(originalUrl);

      if (URLUtil.isValidUrl(decodeUrl)) {

        UrlModel urlModel = DeviceUtil.extractUrl(decodeUrl);

        urlTextInputEditText.setText("");
      }
    }
  }

  public boolean isValid(TextInputEditText urlTextInputEditText) {
    return urlTextInputEditText.getText().length() > 0;
  }
}