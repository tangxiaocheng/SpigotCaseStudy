package com.spigot.study.util;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import com.spigot.study.data.DeviceInfo;
import java.util.HashMap;
import java.util.Map;

public class SpigotConstant {

  public static final DiffUtil.ItemCallback<DeviceInfo> DIFF_ITEM_CALLBACK = new DiffUtil.ItemCallback<DeviceInfo>() {
    @Override
    public boolean areItemsTheSame(@NonNull DeviceInfo oldItem, @NonNull DeviceInfo newItem) {
      return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull DeviceInfo oldItem, @NonNull DeviceInfo newItem) {
      return oldItem.equals(newItem);
    }
  };


  public static final Map<String, String> DEVICE_INFO_MAP = new HashMap<String, String>() {
    {
      put("appVersion", String.valueOf(Build.VERSION.SDK_INT));
      put("deviceBrand", Build.BRAND);
      put("deviceModel", Build.MODEL);
      put("display", Build.DISPLAY);
      put("hardware", Build.HARDWARE);
      put("fingerprint", Build.FINGERPRINT);
      put("host", Build.HOST);
      put("buildId", Build.ID);
      put("buildUser", Build.USER);
      put("screen", DeviceUtil.getScreenMetrics());
    }
  };
}
