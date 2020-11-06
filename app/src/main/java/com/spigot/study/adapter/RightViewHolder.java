package com.spigot.study.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.spigot.study.R;
import com.spigot.study.data.DeviceInfo;

class RightViewHolder extends RecyclerView.ViewHolder {

  private final OnItemClickListener onItemClickListener;
  private final TextView infoTv;
  private final Button postBtn;
  private final TextView postResponseTv;

  public RightViewHolder(OnItemClickListener onItemClickListener,
      @NonNull View itemView) {
    super(itemView);
    this.onItemClickListener = onItemClickListener;
    infoTv = itemView.findViewById(R.id.info_tv);
    postBtn = itemView.findViewById(R.id.post);
    postResponseTv = itemView.findViewById(R.id.post_response_tv);
  }

  public void bindData(DeviceInfo deviceInfo) {
    infoTv.setText(deviceInfo.getJson());
    if (onItemClickListener != null) {
      postBtn.setOnClickListener(view -> onItemClickListener.onItemClick(deviceInfo));
    }
  }

  public void clear() {
    infoTv.invalidate();
    postBtn.invalidate();
    postResponseTv.invalidate();
  }
}