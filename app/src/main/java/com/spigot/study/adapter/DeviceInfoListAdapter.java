package com.spigot.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.spigot.study.R;
import com.spigot.study.data.DeviceInfo;
import com.spigot.study.util.SpigotConstant;

public class DeviceInfoListAdapter extends
    PagedListAdapter<DeviceInfo, RecyclerView.ViewHolder> {

  private static final int TYPE_LEFT = 0;
  private static final int TYPE_RIGHT = 1;
  private final LayoutInflater layoutInflater;

  public DeviceInfoListAdapter(
      Context context) {
    super(SpigotConstant.DIFF_ITEM_CALLBACK);
    this.layoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    switch (viewType) {
      case TYPE_LEFT: {
        View itemView = layoutInflater.inflate(R.layout.device_info_list_item_left, parent, false);
        return new LeftViewHolder(itemView);
      }
      case TYPE_RIGHT: {
        View itemView = layoutInflater.inflate(R.layout.device_info_list_item_right, parent, false);
        return new RightViewHolder(itemView);
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

  static class LeftViewHolder extends RecyclerView.ViewHolder {

    TextView infoTv;
    Button postBtn;
    TextView postResponseTv;

    public LeftViewHolder(@NonNull View itemView) {
      super(itemView);
      infoTv = itemView.findViewById(R.id.info_tv);
      postBtn = itemView.findViewById(R.id.post);
      postResponseTv = itemView.findViewById(R.id.post_response_tv);
    }

    public void bindData(DeviceInfo deviceInfo) {
      infoTv.setText(deviceInfo.getJson());
    }

    public void clear() {
      infoTv.invalidate();
      postBtn.invalidate();
      postResponseTv.invalidate();
    }
  }

  static class RightViewHolder extends RecyclerView.ViewHolder {

    TextView infoTv;
    Button postBtn;
    TextView postResponseTv;

    public RightViewHolder(@NonNull View itemView) {
      super(itemView);
      infoTv = itemView.findViewById(R.id.info_tv);
      postBtn = itemView.findViewById(R.id.post);
      postResponseTv = itemView.findViewById(R.id.post_response_tv);
    }

    public void bindData(DeviceInfo deviceInfo) {
      infoTv.setText(deviceInfo.getJson());
    }

    public void clear() {
      infoTv.invalidate();
      postBtn.invalidate();
      postResponseTv.invalidate();
    }
  }

}
