package com.spigot.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.spigot.study.R;
import com.spigot.study.room.DeviceInfo;
import com.spigot.study.util.SpigotConstant;
import java.util.Objects;

public class DeviceInfoListAdapter extends PagedListAdapter<DeviceInfo, RecyclerView.ViewHolder> {

  private final OnItemClickListener onItemClickListener;
  public static final int TYPE_LEFT = 0;
  public static final int TYPE_RIGHT = 1;
  private final LayoutInflater layoutInflater;

  public DeviceInfoListAdapter(OnItemClickListener onItemClickListener, Context context) {
    super(SpigotConstant.DIFF_ITEM_CALLBACK);
    this.onItemClickListener = onItemClickListener;
    this.layoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    switch (viewType) {
      case TYPE_LEFT:
        {
          return new LeftViewHolder(
              onItemClickListener,
              layoutInflater.inflate(R.layout.device_info_list_item_left, parent, false));
        }
      case TYPE_RIGHT:
        {
          return new RightViewHolder(
              onItemClickListener,
              layoutInflater.inflate(R.layout.device_info_list_item_right, parent, false));
        }
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    DeviceInfo deviceInfo1 = Objects.requireNonNull(getCurrentList()).get(position);
    switch (Objects.requireNonNull(deviceInfo1).getViewType()) {
      case TYPE_LEFT:
        {
          bindLeftData((LeftViewHolder) holder, deviceInfo1);
          break;
        }
      case TYPE_RIGHT:
        {
          bindRightData((RightViewHolder) holder, deviceInfo1);
          break;
        }
    }
  }

  private void bindLeftData(@NonNull LeftViewHolder holder, DeviceInfo deviceInfo) {
    if (deviceInfo != null) {
      holder.bindData(deviceInfo);
    } else {
      holder.clear();
    }
  }

  private void bindRightData(@NonNull RightViewHolder holder, DeviceInfo deviceInfo) {
    if (deviceInfo != null) {
      holder.bindData(deviceInfo);
    } else {
      holder.clear();
    }
  }

  @Override
  public int getItemViewType(int position) {
    return Objects.requireNonNull(Objects.requireNonNull(getCurrentList()).get(position))
        .getViewType();
  }
}
