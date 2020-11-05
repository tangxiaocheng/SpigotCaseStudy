package com.spigot.study.model;

import android.util.Pair;
import java.util.List;

public class UrlModel {

  String baseUrl;
  List<Pair<String, String>> pairList;

  public UrlModel(String baseUrl,
      List<Pair<String, String>> pairList) {
    this.baseUrl = baseUrl;
    this.pairList = pairList;
  }

  public String toUrl() {
    StringBuilder sb = new StringBuilder();
    sb.append(baseUrl).append("?");
    for (Pair<String, String> pair : pairList) {
      sb.append(pair.first).append("=").append(pair.second).append("&");
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }
}