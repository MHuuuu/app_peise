package com.example.peisp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peisp.R;
import com.example.peisp.model.AttendanceRecord;
import com.example.peisp.model.Worker;

import java.util.ArrayList;
import java.util.List;

public class WorkerDetailAdapter extends BaseAdapter {

    private Context context;
    private List<String[]> data;

    public WorkerDetailAdapter(Context c,List<String[]> data) {
        this.context = c;
        this.data = data;
    }

    public WorkerDetailAdapter(Context c) {
        this.context = c;
    }

    public void updateData(List<String[]> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        // 重用convertView
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_worker_detail_list_common, viewGroup,false);
            holder.ivState = (ImageView) convertView.findViewById(R.id.iv_state);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tvInfo = (TextView) convertView.findViewById(R.id.tv_info);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 适配数据
//        holder.ivState.setText(data.get(i).getWorker().getName());
        holder.tvTime.setText(data.get(i)[0]);
        holder.tvInfo.setText(data.get(i)[1]);

        return convertView;
    }

    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder {
        public ImageView ivState;
        public TextView tvTime;
        public TextView tvInfo;
    }

}
