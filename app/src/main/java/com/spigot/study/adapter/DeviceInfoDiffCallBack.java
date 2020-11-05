package com.spigot.study.adapter;

import androidx.annotation.NonNull;
import com.spigot.study.data.DeviceInfo;

final class DeviceInfoDiffCallBack extends
    androidx.recyclerview.widget.DiffUtil.ItemCallback<DeviceInfo> {

  public boolean areItemsTheSame(
      @NonNull DeviceInfo oldDeviceInfo, @NonNull DeviceInfo newDeviceInfo) {
    return oldDeviceInfo.getId() == newDeviceInfo.getId();
  }

  @Override
  public boolean areContentsTheSame(
      @NonNull DeviceInfo oldDeviceInfo, @NonNull DeviceInfo newDeviceInfo) {
    return oldDeviceInfo.equals(newDeviceInfo);
  }
}
