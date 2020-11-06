package com.spigot.study.util;

import android.content.res.Resources;
import android.os.Build;
import com.spigot.study.model.UrlModel;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import timber.log.Timber;

public class DeviceUtil {


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


  public static String getScreenMetrics() {
    return getScreenWidth() + "*" + getScreenHeight();
  }

  private static int getScreenWidth() {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  private static int getScreenHeight() {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }

  public static String getDeviceInfo() {
    String details = "VERSION.RELEASE : " + Build.VERSION.RELEASE
        + "\nVERSION.INCREMENTAL : " + Build.VERSION.INCREMENTAL
        + "\nVERSION.SDK.NUMBER : " + Build.VERSION.SDK_INT
        + "\nBOARD : " + Build.BOARD
        + "\nBOOTLOADER : " + Build.BOOTLOADER
        + "\nBRAND : " + Build.BRAND
        + "\nCPU_ABI : " + Build.CPU_ABI
        + "\nCPU_ABI2 : " + Build.CPU_ABI2
        + "\nDISPLAY : " + Build.DISPLAY
        + "\nFINGERPRINT : " + Build.FINGERPRINT
        + "\nHARDWARE : " + Build.HARDWARE
        + "\nHOST : " + Build.HOST
        + "\nID : " + Build.ID
        + "\nMANUFACTURER : " + Build.MANUFACTURER
        + "\nMODEL : " + Build.MODEL
        + "\nPRODUCT : " + Build.PRODUCT
        + "\nSERIAL : " + Build.SERIAL
        + "\nTAGS : " + Build.TAGS
        + "\nTIME : " + Build.TIME
        + "\nTYPE : " + Build.TYPE
        + "\nUNKNOWN : " + Build.UNKNOWN
        + "\nUSER : " + Build.USER;

    return details;
  }

}
