package com.example.peisp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;
import com.example.peisp.adapter.ManagerExtendableListViewAdapter;

public class ManageMainActivity extends AppCompatActivity {
    private ImageView mIvBack;
    private ExpandableListView mExpendList;

    //显示测试用
    /*public String[] groupString = {"射手", "辅助", "坦克", "法师"};
    public String[][] childString = {
            {"孙尚香", "后羿", "马可波罗", "狄仁杰"},
            {"孙膑", "蔡文姬", "鬼谷子", "杨玉环"},
            {"张飞", "廉颇", "牛魔", "项羽"},
            {"诸葛亮", "王昭君", "安琪拉", "干将"}
    };*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        initView();

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(ManageMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mExpendList.setAdapter(new ManagerExtendableListViewAdapter());
        //设置分组的监听
        mExpendList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Toast.makeText(getApplicationContext(), groupString[groupPosition], Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //设置子项布局监听
        mExpendList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(getApplicationContext(), childString[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(ManageMainActivity.this, WorkDetailActivity.class);
                startActivity(intent);
                return true;
            }
        });
        //控制他只能打开一个组
        mExpendList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int count = new ManagerExtendableListViewAdapter().getGroupCount();
                for (int i = 0; i < count; i++) {
                    if (i != groupPosition) {
                        mExpendList.collapseGroup(i);
                    }
                }
            }
        });

    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mExpendList = (ExpandableListView) findViewById(R.id.expend_list);
    }
}
