package com.spigot.study.data;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
    entities = {DeviceInfo.class},
    version = 1,
    exportSchema = false)
public abstract class SpigotDatabase extends RoomDatabase {

  public static final String VIDEO_DB = "device_info_db_java";
  private static final int NUMBER_OF_THREADS = 4;
  static final ExecutorService databaseWriteExecutor =
      Executors.newFixedThreadPool(NUMBER_OF_THREADS);
  private static volatile SpigotDatabase INSTANCE;
  private static final Callback roomDbCallback =
      new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
          super.onOpen(db);
//          databaseWriteExecutor.execute( SpigotDatabase::insertMockData);
        }
      };

  private static void insertMockData() {
    for (int i = 0; i < 40; i++) {
      INSTANCE.deviceInfoDao()
          .insert(
              new DeviceInfo("title" + i, 150 + "",
                  System.currentTimeMillis()));
    }
  }

  static SpigotDatabase getDataBase(final Context context) {
    if (INSTANCE == null) {
      synchronized ((SpigotDatabase.class)) {
        if (INSTANCE == null) {
          INSTANCE =
              Room.databaseBuilder(
                  context.getApplicationContext(), SpigotDatabase.class, VIDEO_DB)
                  .addCallback(roomDbCallback)
                  .build();
        }
      }
    }
    return INSTANCE;
  }

  public abstract DeviceInfoDao deviceInfoDao();
}
