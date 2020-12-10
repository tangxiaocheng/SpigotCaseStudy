package com.spigot.study;

import static org.hamcrest.core.IsEqual.equalTo;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.spigot.study.room.DeviceInfo;
import com.spigot.study.room.DeviceInfoDao;
import com.spigot.study.room.SpigotDatabase;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DeviceInfoEntityReadWriteTest {

  private DeviceInfoDao deviceInfoDao;
  private SpigotDatabase db;

  @Before
  public void createDb() {
    Context context = ApplicationProvider.getApplicationContext();
    db = Room.inMemoryDatabaseBuilder(context, SpigotDatabase.class).build();
    deviceInfoDao = db.deviceInfoDao();
  }

  @After
  public void closeDb() {
    db.close();
  }

  @Test
  public void writeAndReadInList() {
    DeviceInfo deviceInfo = new DeviceInfo("www.hello.com", "this is ", System.currentTimeMillis());
    deviceInfoDao.insert(deviceInfo);
    List<DeviceInfo> list = deviceInfoDao.getList();
    Assert.assertThat(list.get(0).getBaseUrl(), equalTo(deviceInfo.getBaseUrl()));
  }

  @Test
  public void insertALot() {
    int times = 50;
    for (int i = 0; i < times; i++) {
      DeviceInfo deviceInfo =
          new DeviceInfo("www.hello.com", "this is ", System.currentTimeMillis());
      deviceInfoDao.insert(deviceInfo);
    }
    List<DeviceInfo> list = deviceInfoDao.getList();
    Assert.assertThat(list.size(), equalTo(times));
  }
}
