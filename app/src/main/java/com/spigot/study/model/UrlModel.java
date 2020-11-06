package com.spigot.study.model;

import java.util.Map;

public class UrlModel {

  public String getBaseUrl() {
    return baseUrl;
  }

  public Map<String, String> getParaMap() {
    return paraMap;
  }

  public void setParaMap(Map<String, String> paraMap) {
    this.paraMap = paraMap;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  String baseUrl;

  Map<String, String> paraMap;

  public String toUrl() {
    StringBuilder sb = new StringBuilder();
    sb.append(baseUrl).append("?");
    for (String key : paraMap.keySet()) {
      sb.append(key).append("=").append(paraMap.get(key)).append("&");
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

}
