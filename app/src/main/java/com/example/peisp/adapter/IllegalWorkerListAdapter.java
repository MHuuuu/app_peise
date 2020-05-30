package com.example.peisp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.peisp.R;
import com.example.peisp.model.IllegalRecord;
import com.example.peisp.model.Worker;

import java.util.ArrayList;
import java.util.List;

public class IllegalWorkerListAdapter extends BaseAdapter {

    Context context;

    //tmp
    List<Worker> workers = new ArrayList<Worker>() {
        {
            add(new Worker("王石", "2", "机床操作员", "[hc5678910]", "178****1234"));
            add(new Worker("于清教", "2", "机床操作员", "[hc5678910]", "178****1234"));
            add(new Worker("于清教", "2", "机床操作员", "[hc5678910]", "178****1234"));
        }
    };

    List<IllegalRecord> data = new ArrayList<IllegalRecord>() {
        {
            add(new IllegalRecord(1,workers.get(0),"未佩戴安全帽","刚刚"));
            add(new IllegalRecord(1,workers.get(1),"未佩戴安全帽","刚刚"));
            add(new IllegalRecord(1,workers.get(2),"未扣紧安全帽..","2个小时前"));
            add(new IllegalRecord(1,workers.get(0),"未佩戴安全帽","3个小时前"));
            add(new IllegalRecord(1,workers.get(1),"未扣紧安全帽..","4个小时前"));
            add(new IllegalRecord(1,workers.get(2),"未佩戴安全帽","10个小时前"));
        }
    };

    public IllegalWorkerListAdapter(Context c, List<IllegalRecord> data) {
        this.context = c;
        this.data = data;
    }

    public IllegalWorkerListAdapter(Context c) {
        this.context = c;
    }

    public void updateData(List<IllegalRecord> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_illegal_record_worker, viewGroup,false);
//            convertView = LayoutInflater.from(context).inflate(R.layout.item_illegal_record_worker, null);
            holder.ivIllWorkerName = (TextView) convertView.findViewById(R.id.iv_ill_worker_name);
            holder.ivIllWorkerJid = (TextView) convertView.findViewById(R.id.iv_ill_worker_jid);
            holder.ivIllDetail = (TextView) convertView.findViewById(R.id.iv_ill_detail);
            holder.ivIllTime = (TextView) convertView.findViewById(R.id.iv_ill_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 适配数据
        holder.ivIllWorkerName.setText(data.get(i).getWorker().getName());
        holder.ivIllWorkerJid.setText(data.get(i).getWorker().getJobNumber());
        holder.ivIllDetail.setText(data.get(i).getDetail());
        holder.ivIllTime.setText(data.get(i).getTime());

        return convertView;
    }

    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder {
        public TextView ivIllWorkerName;
        public TextView ivIllWorkerJid;
        public TextView ivIllDetail;
        public TextView ivIllTime;
    }

}
