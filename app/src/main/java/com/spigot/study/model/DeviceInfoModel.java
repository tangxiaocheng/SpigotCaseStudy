package com.spigot.study.model;

public class DeviceInfoModel {

  String deviceID;
  String manufacturer;
  String model;
  String sdkNumber;
  String host;
  String serial;
  String brand;
  String display;
  String displayMetrics;

  public DeviceInfoModel(String deviceID, String manufacturer, String model,
      String sdkNumber, String host, String serial, String brand, String display,
      String displayMetrics) {
    this.deviceID = deviceID;
    this.manufacturer = manufacturer;
    this.model = model;
    this.sdkNumber = sdkNumber;
    this.host = host;
    this.serial = serial;
    this.brand = brand;
    this.display = display;
    this.displayMetrics = displayMetrics;
  }
}
