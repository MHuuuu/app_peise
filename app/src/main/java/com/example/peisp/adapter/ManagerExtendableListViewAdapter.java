package com.example.peisp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.peisp.R;
import com.example.peisp.model.Department;
import com.example.peisp.model.Worker;

public class ManagerExtendableListViewAdapter extends BaseExpandableListAdapter {
    private Context mcontext;
    static public Department[] groupString={
            new Department("车间A",7),
            new Department("车间B",27),
            new Department("车间C",19),
            new Department("车间D",5)
    };
    static public Worker[][] childString={
            { new Worker("王石",groupString[0],"机床操作员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[0],"机床操作员","[hc5678911]","178****1234"),
                    new Worker("于清教",groupString[0],"叉车驾驶员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[0],"机床操作员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[0],"叉车驾驶员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[0],"机床操作员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[0],"叉车驾驶员","[hc5678910]","178****1234")},
            { new Worker("王石",groupString[1],"机床操作员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[1],"机床操作员","[hc5678911]","178****1234"),
                    new Worker("于清教",groupString[1],"叉车驾驶员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[1],"机床操作员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[1],"叉车驾驶员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[1],"机床操作员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[1],"叉车驾驶员","[hc5678910]","178****1234")},
            { new Worker("王石",groupString[2],"机床操作员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[2],"机床操作员","[hc5678911]","178****1234"),
                    new Worker("于清教",groupString[2],"叉车驾驶员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[2],"机床操作员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[2],"机床操作员","[hc5678910]","178****1234"),
                    new Worker("王石",groupString[2],"叉车驾驶员","[hc5678910]","178****1234")},
            { new Worker("陈嘉敏",groupString[3],"机床操作员","[hc5678910]","178****1234"),
                    new Worker("陈嘉敏",groupString[3],"机床操作员","[hc5678911]","178****1234"),
                    new Worker("陈嘉敏",groupString[3],"机床操作员","[hc5678911]","178****1234"),
                    new Worker("陈嘉敏",groupString[3],"机床操作员","[hc5678911]","178****1234"),
                    new Worker("陈嘉敏",groupString[3],"机床操作员","[hc5678911]","178****1234")}
    };


    @Override
    // 获取分组的个数
    public int getGroupCount() {
        return groupString.length;
    }

    //获取指定分组中的子选项的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return childString[groupPosition].length;
    }

    //        获取指定的分组数据
    @Override
    public Object getGroup(int groupPosition) {
        return groupString[groupPosition];
    }

    //获取指定分组中的指定子选项数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childString[groupPosition][childPosition];
    }

    //获取指定分组的ID, 这个ID必须是唯一的
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取子选项的ID, 这个ID必须是唯一的
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    @Override
    public boolean hasStableIds() {
        return true;
    }
    /**
     *
     * 获取显示指定组的视图对象
     *
     * @param groupPosition 组位置
     * @param isExpanded 该组是展开状态还是伸缩状态
     * @param convertView 重用已有的视图对象
     * @param parent 返回的视图对象始终依附于的视图组
     */
// 获取显示指定分组的视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expend_list_partent,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvName = (TextView)convertView.findViewById(R.id.label_dep_name);
            groupViewHolder.tvNum = (TextView)convertView.findViewById(R.id.label_dep_num);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.tvName.setText(groupString[groupPosition].getName());
        groupViewHolder.tvNum.setText(groupString[groupPosition].getWorkerNum().toString());
        return convertView;
    }
    /**
     *
     * 获取一个视图对象，显示指定组中的指定子元素数据。
     *
     * @param groupPosition 组位置
     * @param childPosition 子元素位置
     * @param isLastChild 子元素是否处于组中的最后一个
     * @param convertView 重用已有的视图(View)对象
     * @param parent 返回的视图(View)对象始终依附于的视图组
     * @return
     * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View,
     *      android.view.ViewGroup)
     */

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expend_list_child,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvWorkerName = (TextView)convertView.findViewById(R.id.label_worker_name);
            childViewHolder.tvWorkerJID = (TextView)convertView.findViewById(R.id.label_work_jid);
            childViewHolder.tvWorkerStation = (TextView)convertView.findViewById(R.id.label_work_station);
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.tvWorkerName.setText(childString[groupPosition][childPosition].getName());
        childViewHolder.tvWorkerJID.setText(childString[groupPosition][childPosition].getJobNumber());
        childViewHolder.tvWorkerStation.setText(childString[groupPosition][childPosition].getStation());
        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        TextView tvName;
        TextView tvNum;
    }

    static class ChildViewHolder {
        TextView tvWorkerName;
        TextView tvWorkerJID;
        TextView tvWorkerStation;
    }
}