package com.spigot.study.data;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface DeviceInfoDao {

  @Insert
  void insert(DeviceInfo deviceInfo);

  @Query("DELETE FROM header_info_table")
  void deleteAll();

  @Query("SELECT * from header_info_table ORDER BY createdTime DESC")
  LiveData<List<DeviceInfo>> getListByAlphabetizedInTittle();

  @Query("SELECT * from header_info_table ORDER BY createdTime DESC")
  List<DeviceInfo> getList();


  @Query("SELECT * FROM header_info_table ORDER BY createdTime DESC")
  DataSource.Factory<Integer, DeviceInfo> pageSizeVideo();
}
