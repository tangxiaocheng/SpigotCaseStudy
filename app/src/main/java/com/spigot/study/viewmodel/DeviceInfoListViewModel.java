package com.spigot.study.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.spigot.study.data.DeviceInfo;
import com.spigot.study.data.DeviceInfoRepository;

public class DeviceInfoListViewModel extends AndroidViewModel {


  private final LiveData<PagedList<DeviceInfo>> liveDataOfPagedList;

  public DeviceInfoRepository getDeviceInfoRepository() {
    return deviceInfoRepository;
  }

  private final DeviceInfoRepository deviceInfoRepository;

  public DeviceInfoListViewModel(Application application) {
    super(application);
    deviceInfoRepository = new DeviceInfoRepository(application);
    PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(false)
        .setPrefetchDistance(20)
        .setInitialLoadSizeHint(10).setPageSize(10).build();
    liveDataOfPagedList = new LivePagedListBuilder<>(deviceInfoRepository.liveDataOfPagedList(),
        config).build();
  }

  public LiveData<PagedList<DeviceInfo>> getLiveDataOfPagedList() {
    return liveDataOfPagedList;
  }
}
