package com.spigot.study.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.spigot.study.R;
import com.spigot.study.room.DeviceInfo;
import com.spigot.study.util.Util;
import java.util.HashMap;

class LeftViewHolder extends RecyclerView.ViewHolder {

  private final OnItemClickListener onItemClickListener;
  private final TextView infoTv;
  private final Button postBtn;
  private final Button formatBtn;

  public LeftViewHolder(OnItemClickListener onItemClickListener,
      @NonNull View itemView) {
    super(itemView);
    this.onItemClickListener = onItemClickListener;
    infoTv = itemView.findViewById(R.id.info_tv);
    postBtn = itemView.findViewById(R.id.post);
    formatBtn = itemView.findViewById(R.id.format);

  }

  public void bindData(DeviceInfo deviceInfo) {

    infoTv.setText(deviceInfo.getJson());
    if (onItemClickListener != null) {
      postBtn.setOnClickListener(view -> onItemClickListener.onItemClick(deviceInfo));
      formatBtn.setOnClickListener(view -> {
        HashMap<String, String> map = Util.jsonToMap(deviceInfo.getJson());
        infoTv.setText(Util.prettyJson(map));
      });
    }
  }

  public void clear() {
    infoTv.invalidate();
    postBtn.invalidate();
  }
}