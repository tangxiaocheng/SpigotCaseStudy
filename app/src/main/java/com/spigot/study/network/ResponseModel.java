package com.spigot.study.network;

import java.util.Map;

public class ResponseModel {

  boolean successful;
  String errorMsg;

  public Map<String, String> getRequest() {
    return request;
  }

  public void setRequest(Map<String, String> request) {
    this.request = request;
  }

  Map<String, String> request;


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
