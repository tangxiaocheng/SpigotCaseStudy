package com.spigot.study.util;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spigot.study.room.DeviceInfo;
import java.util.HashMap;
import java.util.Map;

public class SpigotConstant {

  public static final String ANDROID_ID = "androidID";
  public static final String APP_VERSION = "appVersion";
  public static final String DEVICE_BRAND = "deviceBrand";
  public static final String DEVICE_MODEL = "deviceModel";
  public static final String DISPLAY = "display";
  public static final String HARDWARE = "hardware";
  public static final String FINGERPRINT = "fingerprint";
  public static final String HOST = "host";
  public static final String BUILD_ID = "buildId";
  public static final String BUILD_USER = "buildUser";
  public static final String SCREEN = "screen";
  public static final String KEY_JSON = "Json";
  public static final String UTF_8 = "UTF-8";

  public static final DiffUtil.ItemCallback<DeviceInfo> DIFF_ITEM_CALLBACK =
      new DiffUtil.ItemCallback<DeviceInfo>() {
        @Override
        public boolean areItemsTheSame(@NonNull DeviceInfo oldItem, @NonNull DeviceInfo newItem) {
          return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(
            @NonNull DeviceInfo oldItem, @NonNull DeviceInfo newItem) {
          return oldItem.equals(newItem);
        }
      };

  public static final Map<String, String> DEVICE_INFO_MAP =
      new HashMap<String, String>() {
        {
          put(APP_VERSION, String.valueOf(Build.VERSION.SDK_INT));
          put(DEVICE_BRAND, Build.BRAND);
          put(DEVICE_MODEL, Build.MODEL);
          put(DISPLAY, Build.DISPLAY);
          put(HARDWARE, Build.HARDWARE);
          put(FINGERPRINT, Build.FINGERPRINT);
          put(HOST, Build.HOST);
          put(BUILD_ID, Build.ID);
          put(BUILD_USER, Build.USER);
          put(SCREEN, Util.getScreenMetrics());
        }
      };

  public static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();
}
