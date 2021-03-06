package com.spigot.study.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "header_info_table")
public class DeviceInfo {

  final String baseUrl;
  final String json;
  final long createdTime;

  @PrimaryKey(autoGenerate = true)
  int id;

  public DeviceInfo(String baseUrl, String json, long createdTime) {
    this.baseUrl = baseUrl;
    this.json = json;
    this.createdTime = createdTime;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public String getJson() {
    return json;
  }

  public long getCreatedTime() {
    return createdTime;
  }

  public int getId() {
    return id;
  }

  public int getViewType() {
    // fake view type
    return id % 2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceInfo that = (DeviceInfo) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return id;
  }
}
