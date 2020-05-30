package com.example.peisp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;
import com.example.peisp.adapter.IllegalWorkerListAdapter;
import com.example.peisp.model.IllegalRecord;

import java.util.ArrayList;
import java.util.List;

public class IllegalRecordWorkerActivity extends AppCompatActivity {
    private ImageView mIvBack;
    private ListView mLvIllegalWorkerList;
    private IllegalWorkerListAdapter workerListAdapter;
    private List<IllegalRecord> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegal_record_worker);
        initView();
        initData();

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(IllegalRecordWorkerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mLvIllegalWorkerList = (ListView) findViewById(R.id.lv_illegal_worker_list);

        mLvIllegalWorkerList.setAdapter(workerListAdapter);
    }

    private void initData() {
        // 初始化数据
        //data = new ArrayList<>();
        // 初始化适配器
        //workerListAdapter = new IllegalWorkerListAdapter(getApplicationContext(), data);
        //mLvIllegalWorkerList.setAdapter(workerListAdapter);
    }


}
