package com.spigot.study.util;

import static com.spigot.study.util.SpigotConstant.DEVICE_INFO_MAP;

import android.content.res.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spigot.study.model.UrlModel;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import timber.log.Timber;

public class Util {


  public static UrlModel extractUrl(String url,
      final Map<String, String> map) {
    UrlModel urlModel = new UrlModel();
    if (null != url && !"".equals(url)) {
      int pos = url.indexOf("?");
      if (pos != -1) {
        urlModel.setBaseUrl(url.substring(0, pos));
        String pairString = url.substring(pos + 1);
        String[] parameters = pairString.split("&");
        for (String pairStr : parameters) {
          String[] pairArr = pairStr.split("=");
          if (null != pairArr && pairArr.length == 2) {
            map.put(pairArr[0], pairArr[1]);
          }
        }
      } else {
        Timber.e("no parameters or invalid url");
      }

    }
    urlModel.setParaMap(map);
    return urlModel;
  }

  public static String decodeUrl(String originalUrl) {
    String afterDecode = null;
    try {
      afterDecode = URLDecoder.decode(originalUrl, SpigotConstant.UTF_8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return afterDecode;
  }

  public static String encodeUrl(String url) {
    String encode = null;
    try {
      encode = URLEncoder.encode(url, SpigotConstant.UTF_8);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return encode;
  }


  public static String getScreenMetrics() {
    return getScreenWidth() + "*" + getScreenHeight();
  }

  private static int getScreenWidth() {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  private static int getScreenHeight() {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }

  public static HashMap<String, String> jsonToMap(String jsonString) {
    Gson gson = new Gson();
    Type type = new TypeToken<HashMap<String, String>>() {
    }.getType();
    return gson.fromJson(jsonString, type);
  }

  public static Map<String, String> getDeviceInfoModel(String androidId) {
    Map<String, String> map = new HashMap<>(DEVICE_INFO_MAP);
    map.put(SpigotConstant.ANDROID_ID, androidId);
    return map;
  }

  public static String prettyJson(Object obj) {
    return SpigotConstant.PRETTY_GSON.toJson(obj);
  }
}
