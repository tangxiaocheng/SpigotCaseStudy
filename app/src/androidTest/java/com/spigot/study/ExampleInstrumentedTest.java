package com.spigot.study;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.spigot.study.model.UrlModel;
import com.spigot.study.network.NetworkService;
import com.spigot.study.network.ResponseModel;
import com.spigot.study.network.RetrofitInstance;
import com.spigot.study.util.DeviceUtil;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import retrofit2.Call;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

  String originalUrl = "https://m.alltheapps.org/get/app?userId=B1C92850-8202-44AC-B514-1849569F37B6&implementationid=cl-and-erp&trafficSource=erp&userClass=20200101";

  @Test
  public void useAppContext() {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    assertEquals("com.spigot.study", appContext.getPackageName());
  }

  @Test
  public void extractUrlIsCorrect() {
    UrlModel expected = DeviceUtil.extractUrl(originalUrl);
    Assert.assertEquals(expected.toUrl(), originalUrl);
  }

  @Test
  public void encodeAndDecodeIsCorrect() {
    String encodeUrl = DeviceUtil.encodeUrl(originalUrl);
    Assert.assertEquals(DeviceUtil.decodeUrl(encodeUrl), originalUrl);
  }

  @Test
  public void printDeviceInfo() {
    System.out.println(DeviceUtil.getDeviceInfo());
  }


}