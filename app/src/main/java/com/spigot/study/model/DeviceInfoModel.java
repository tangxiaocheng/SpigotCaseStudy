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

  @Override
  public String toString() {
    return "DeviceInfoModel{" +
        "deviceID='" + deviceID + '\'' +
        ", manufacturer='" + manufacturer + '\'' +
        ", model='" + model + '\'' +
        ", sdkNumber='" + sdkNumber + '\'' +
        ", host='" + host + '\'' +
        ", serial='" + serial + '\'' +
        ", brand='" + brand + '\'' +
        ", display='" + display + '\'' +
        ", displayMetrics='" + displayMetrics + '\'' +
        '}';
  }

  public String getDeviceID() {
    return deviceID;
  }

  public void setDeviceID(String deviceID) {
    this.deviceID = deviceID;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getSdkNumber() {
    return sdkNumber;
  }

  public void setSdkNumber(String sdkNumber) {
    this.sdkNumber = sdkNumber;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public String getDisplayMetrics() {
    return displayMetrics;
  }

  public void setDisplayMetrics(String displayMetrics) {
    this.displayMetrics = displayMetrics;
  }
}
