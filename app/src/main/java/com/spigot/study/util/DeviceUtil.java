package com.spigot.study.util;

import android.util.Pair;
import com.spigot.study.model.UrlModel;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

public class DeviceUtil {

  public static UrlModel extractUrl(String url) {
    UrlModel urlModel = null;
    if (null != url && !"".equals(url)) {
      int pos = url.indexOf("?");
      String baseUrl = url.substring(0, pos);
      String pairString = url.substring(pos + 1);
      String[] parameters = pairString.split("&");
      ArrayList<Pair<String, String>> list = new ArrayList<>(parameters.length);
      for (String pairStr : parameters
      ) {
        String[] pairArr = pairStr.split("=");
        System.out.println(Arrays.toString(pairArr));
        Pair<String, String> pair = new Pair<>(pairArr[0], pairArr[1]);
        list.add(pair);
      }
      urlModel = new UrlModel(baseUrl, list);
    }
    return urlModel;
  }

  public static String decodeUrl(String originalUrl) {
    String afterDecode = null;
    try {
      afterDecode = URLDecoder.decode(originalUrl, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return afterDecode;
  }

  public static String encodeUrl(String url) {
    String encode = null;
    try {
      encode = URLEncoder.encode(url, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return encode;
  }
}
