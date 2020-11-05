package com.spigot.study.model;

import android.util.Pair;
import java.util.List;

public class HeaderModel {

  DeviceInfoModel deviceInfoModel;
  List<Pair<String, String>> userInfo;

  public HeaderModel(DeviceInfoModel deviceInfoModel,
      List<Pair<String, String>> userInfo) {
    this.deviceInfoModel = deviceInfoModel;
    this.userInfo = userInfo;
  }

  public DeviceInfoModel getDeviceInfoModel() {
    return deviceInfoModel;
  }

  public void setDeviceInfoModel(DeviceInfoModel deviceInfoModel) {
    this.deviceInfoModel = deviceInfoModel;
  }

  public List<Pair<String, String>> getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(List<Pair<String, String>> userInfo) {
    this.userInfo = userInfo;
  }
}
