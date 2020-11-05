package com.spigot.study.network;

import com.spigot.study.model.DeviceInfoModel;

public class ResponseModel {

  boolean successful;
  String errorMsg;
  DeviceInfoModel request;

  public DeviceInfoModel getRequest() {
    return request;
  }

  public void setRequest(DeviceInfoModel request) {
    this.request = request;
  }

  @Override
  public String toString() {
    return "ResponseModel{" +
        "successful=" + successful +
        ", errorMsg='" + errorMsg + '\'' +
        ", request=" + request +
        '}';
  }

  public boolean isSuccessful() {
    return successful;
  }

  public void setSuccessful(boolean successful) {
    this.successful = successful;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
}
