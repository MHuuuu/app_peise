package com.example.peisp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.peisp.R;
import com.example.peisp.model.Trace;

import java.util.ArrayList;
import java.util.List;

public class TraceListAdapter extends BaseAdapter {
    private Context context;
    private List<Trace> traceList = new ArrayList<>(1);
    private static final int TYPE_TOP = 0x0000;
    private static final int TYPE_NORMAL= 0x0001;

    public TraceListAdapter(Context context, List<Trace> traceList) {
        this.context = context;
        this.traceList = traceList;
    }

    @Override
    public int getCount() {
        return traceList.size();
    }

    @Override
    public Trace getItem(int position) {
        return traceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Trace trace = getItem(position);
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_trace, parent, false);
            holder.ivIllPic = (ImageView) convertView.findViewById(R.id.iv_Ill_pic);
            holder.ivIllPic2 = (ImageView) convertView.findViewById(R.id.iv_Ill_pic_2);
            holder.tvAcceptTime = (TextView) convertView.findViewById(R.id.tv_accept_yime);
            holder.tvAcceptStation = (TextView) convertView.findViewById(R.id.tv_accept_station);
            holder.tvTopLine = (TextView) convertView.findViewById(R.id.tvTopLine);
            holder.tvDot = (TextView) convertView.findViewById(R.id.tvDot);
            convertView.setTag(holder);
        }

        if (getItemViewType(position) == TYPE_TOP) {
            // 第一行头的竖线不显示
            holder.tvTopLine.setVisibility(View.INVISIBLE);
            // 字体颜色加深
            holder.tvAcceptTime.setTextColor(0xff555555);
            holder.tvAcceptStation.setTextColor(0xff555555);
            holder.tvDot.setBackgroundResource(R.drawable.timelline_dot_first);
        } else if (getItemViewType(position) == TYPE_NORMAL) {
            holder.tvTopLine.setVisibility(View.VISIBLE);
            holder.tvAcceptTime.setTextColor(0xff999999);
            holder.tvAcceptStation.setTextColor(0xff999999);
            holder.tvDot.setBackgroundResource(R.drawable.timelline_dot_normal);
        }

        if (trace.getImgIds().length>1){
            holder.ivIllPic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nohat2));
            holder.ivIllPic2.setVisibility(View.VISIBLE);
            holder.ivIllPic2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nohat3));
        }else {
            holder.ivIllPic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nohat3));
            holder.ivIllPic2.setVisibility(View.INVISIBLE);
        }
        holder.tvAcceptTime.setText(trace.getAcceptTime());
        holder.tvAcceptStation.setText(trace.getAcceptStation());
        return convertView;
    }

    @Override
    public int getItemViewType(int id) {
        if (id == 0) {
            return TYPE_TOP;
        }
        return TYPE_NORMAL;
    }

    static class ViewHolder {
        public ImageView ivIllPic, ivIllPic2;
        public TextView tvAcceptTime, tvAcceptStation;
        public TextView tvTopLine, tvDot;
    }
}