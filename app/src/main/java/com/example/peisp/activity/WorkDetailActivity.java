package com.example.peisp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;
import com.example.peisp.adapter.WorkerDetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class WorkDetailActivity extends AppCompatActivity {
    private ImageView mIvBack;
    private TextView mTitleTv;
    private WorkerDetailAdapter wdAdapter1,wdAdapter2;


    private ListView mWgList;
    private ListView mKqList;

    private List<String[]> illRecords = new ArrayList<String[]>() {
        {
            add(new String[]{"1","2020-03-01", "未佩戴安全帽"});
            add(new String[]{"0","2020-03-02", "未佩戴安全帽"});
            add(new String[]{"0","2020-03-03", "未佩戴安全帽"});
            add(new String[]{"1","2020-03-04", "未佩戴安全帽"});
        }
    };

    private List<String[]> attRecords = new ArrayList<String[]>() {
        {
            add(new String[]{"0","2020-03-01", "迟到"});
            add(new String[]{"1","2020-03-02", "缺勤"});
            add(new String[]{"0","2020-03-03", "迟到"});
            add(new String[]{"0","2020-03-04", "早退"});
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_detail);
        initView();
        initList();
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(WorkDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mWgList = (ListView) findViewById(R.id.wg_list);
        mKqList = (ListView) findViewById(R.id.kq_list);
        mTitleTv = (TextView) findViewById(R.id.title_tv);
        mTitleTv.setText("员工信息");
    }

    private void initList() {
        wdAdapter1 = new WorkerDetailAdapter(this, illRecords);
        wdAdapter2 = new WorkerDetailAdapter(this, attRecords);
        mWgList.setAdapter(wdAdapter1);
        mKqList.setAdapter(wdAdapter2);
    }
}
