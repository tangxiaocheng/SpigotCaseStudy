package com.spigot.study.data;

import android.content.Context;
import androidx.paging.DataSource.Factory;

public class DeviceInfoRepository {

  private final DeviceInfoDao deviceInfoDao;

  public DeviceInfoRepository(Context context) {
    SpigotDatabase db = SpigotDatabase
        .getDataBase(context);
    deviceInfoDao = db.deviceInfoDao();
  }


  public void insert(DeviceInfo deviceInfo) {
    SpigotDatabase.databaseWriteExecutor.execute(() -> deviceInfoDao.insert(deviceInfo));
  }

  public Factory<Integer, DeviceInfo> liveDataOfPagedList() {
    return deviceInfoDao.pageSizeVideo();
  }
}
