package com.spigot.study.data;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource.Factory;
import java.util.List;

public class DeviceInfoRepository {

  private final DeviceInfoDao deviceInfoDao;
  private final LiveData<List<DeviceInfo>> liveDataOfDeviceInfoList;

  public DeviceInfoRepository(Context context) {
    SpigotDatabase db = SpigotDatabase
        .getDataBase(context);
    deviceInfoDao = db.deviceInfoDao();
    liveDataOfDeviceInfoList = deviceInfoDao.getListByAlphabetizedInTittle();
  }

  public LiveData<List<DeviceInfo>> getLiveDataOfDeviceInfoList() {
    return liveDataOfDeviceInfoList;
  }

  public void insert(DeviceInfo deviceInfo) {
    SpigotDatabase.databaseWriteExecutor.execute(() -> deviceInfoDao.insert(deviceInfo));
  }

  public Factory<Integer, DeviceInfo> liveDataOfPagedList() {
    return deviceInfoDao.pageSizeVideo();
  }
}
