package com.spigot.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.spigot.study.R;
import com.spigot.study.data.DeviceInfo;

public class DeviceInfoListAdapter extends
    PagedListAdapter<DeviceInfo, DeviceInfoListAdapter.ViewHolder> {

  private final LayoutInflater layoutInflater;

  public DeviceInfoListAdapter(
      Context context) {
    super(DIFF_CALLBACK);
    this.layoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = layoutInflater.inflate(R.layout.device_info_list_item_small, parent, false);
    return new ViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    DeviceInfo deviceInfo = getItem(position);
    if (deviceInfo != null) {
      holder.bindData(deviceInfo);
    } else {
      holder.clear();
    }
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    TextView infoTv;
    Button postBtn;
    TextView postResponseTv;

    public ViewHolder(@NonNull View itemView) {
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

  public static final DiffUtil.ItemCallback<DeviceInfo> DIFF_CALLBACK
      = new DiffUtil.ItemCallback<DeviceInfo>() {
    @Override
    public boolean areItemsTheSame(
        @NonNull DeviceInfo oldDeviceInfo, @NonNull DeviceInfo newDeviceInfo) {
      return oldDeviceInfo.getId() == newDeviceInfo.getId();
    }

    @Override
    public boolean areContentsTheSame(
        @NonNull DeviceInfo oldDeviceInfo, @NonNull DeviceInfo newDeviceInfo) {
      return oldDeviceInfo.equals(newDeviceInfo);
    }
  };
}
