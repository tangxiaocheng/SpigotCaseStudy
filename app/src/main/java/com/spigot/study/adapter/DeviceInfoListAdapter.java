package com.spigot.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.spigot.study.R;
import com.spigot.study.data.DeviceInfo;
import com.spigot.study.util.SpigotConstant;

public class DeviceInfoListAdapter extends
    PagedListAdapter<DeviceInfo, RecyclerView.ViewHolder> {

  private final OnItemClickListener onItemClickListener;
  private static final int TYPE_LEFT = 0;
  private static final int TYPE_RIGHT = 1;
  private final LayoutInflater layoutInflater;

  public DeviceInfoListAdapter(
      OnItemClickListener onItemClickListener, Context context) {
    super(SpigotConstant.DIFF_ITEM_CALLBACK);
    this.onItemClickListener = onItemClickListener;
    this.layoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    switch (viewType) {
      case TYPE_LEFT: {
        View itemView = layoutInflater.inflate(R.layout.device_info_list_item_left, parent, false);
        return new LeftViewHolder(onItemClickListener, itemView);
      }
      case TYPE_RIGHT: {
        View itemView = layoutInflater.inflate(R.layout.device_info_list_item_right, parent, false);
        return new RightViewHolder(onItemClickListener, itemView);
      }
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    DeviceInfo deviceInfo1 = getCurrentList().get(position);
    switch (deviceInfo1.getViewType()) {
      case TYPE_LEFT: {
        bindLeftData((LeftViewHolder) holder, deviceInfo1);
        break;
      }
      case TYPE_RIGHT: {
        bindRightData((RightViewHolder) holder, deviceInfo1);
        break;
      }
    }
  }

  private void bindLeftData(@NonNull LeftViewHolder holder,
      DeviceInfo deviceInfo) {
    if (deviceInfo != null) {
      holder.bindData(deviceInfo);
    } else {
      holder.clear();
    }
  }

  private void bindRightData(@NonNull RightViewHolder holder,
      DeviceInfo deviceInfo) {
    if (deviceInfo != null) {
      holder.bindData(deviceInfo);
    } else {
      holder.clear();
    }
  }

  @Override
  public int getItemViewType(int position) {
    return getCurrentList().get(position).getViewType();
  }


}
