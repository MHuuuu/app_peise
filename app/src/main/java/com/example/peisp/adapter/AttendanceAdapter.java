package com.example.peisp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.peisp.R;
import com.example.peisp.model.AttendanceRecord;
import com.example.peisp.model.Worker;

import java.util.ArrayList;
import java.util.List;

public class AttendanceAdapter extends BaseAdapter {

    Context context;

    //tmp
    List<Worker> workers = new ArrayList<Worker>() {
        {
            add(new Worker("王石", "2", "机床操作员", "[hc5678910]", "178****1234"));
            add(new Worker("于清教", "2", "机床操作员", "[hc5678910]", "178****1234"));
            add(new Worker("于清教", "2", "机床操作员", "[hc5678910]", "178****1234"));
        }
    };

    List<AttendanceRecord> data = new ArrayList<AttendanceRecord>() {
        {
            add(new AttendanceRecord(workers.get(0), "8:57", "上岗"));
            add(new AttendanceRecord(workers.get(1), "8:57", "上岗"));
            add(new AttendanceRecord(workers.get(2), "8:57", "上岗"));
            add(new AttendanceRecord(workers.get(0), "8:57", "迟到"));
            add(new AttendanceRecord(workers.get(1), "8:57", "早退"));
            add(new AttendanceRecord(workers.get(2), "8:57", "早退"));
        }
    };

    /*public IllegalWorkerListAdapter(Context c, List<IllegalRecord> data) {
        this.context = c;
        this.data = data;
    }*/

    public void updateData(List<AttendanceRecord> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_attendance_list, null);
            holder.iv_att_name = (TextView) convertView.findViewById(R.id.iv_att_name);
            holder.iv_att_jid = (TextView) convertView.findViewById(R.id.iv_att_jid);
            holder.iv_att_time = (TextView) convertView.findViewById(R.id.iv_att_time);
            holder.iv_att_state = (TextView) convertView.findViewById(R.id.iv_att_state);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 适配数据
        holder.iv_att_name.setText(data.get(i).getWorker().getName());
        holder.iv_att_jid.setText(data.get(i).getWorker().getJobNumber());
        holder.iv_att_time.setText(data.get(i).getTime());
        holder.iv_att_state.setText(data.get(i).getState());

        return convertView;
    }

    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder {
        public TextView iv_att_name;
        public TextView iv_att_jid;
        public TextView iv_att_time;
        public TextView iv_att_state;
    }

}
