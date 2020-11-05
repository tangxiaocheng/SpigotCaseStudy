package com.spigot.study.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import com.spigot.study.data.DeviceInfo;

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
}
