package com.spigot.study.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
    entities = {DeviceInfo.class},
    version = 1,
    exportSchema = false)
public abstract class SpigotDatabase extends RoomDatabase {

  private static final String VIDEO_DB = "device_info_db_java";

  private static final int NUMBER_OF_THREADS = 4;

  static final ExecutorService databaseWriteExecutor =
      Executors.newFixedThreadPool(NUMBER_OF_THREADS);
  private static volatile SpigotDatabase INSTANCE;

  static SpigotDatabase getDataBase(final Context context) {
    if (INSTANCE == null) {
      synchronized ((SpigotDatabase.class)) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context, SpigotDatabase.class, VIDEO_DB).build();
        }
      }
    }
    return INSTANCE;
  }

  public abstract DeviceInfoDao deviceInfoDao();
}
