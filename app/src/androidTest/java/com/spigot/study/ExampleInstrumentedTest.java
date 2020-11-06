package com.spigot.study;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.spigot.study.model.UrlModel;
import com.spigot.study.util.Util;
import java.util.LinkedHashMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import timber.log.Timber;

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
    UrlModel expected = Util.extractUrl(originalUrl, new LinkedHashMap<>());
    Timber.d(expected.getParaMap().toString());
    Assert.assertEquals(expected.toUrl(), originalUrl);
  }

  @Test
  public void encodeAndDecodeIsCorrect() {
    String encodeUrl = Util.encodeUrl(originalUrl);
    Assert.assertEquals(Util.decodeUrl(encodeUrl), originalUrl);
  }


}