package com.example.peisp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;
import com.example.peisp.adapter.AttendanceAdapter;

public class AttendanceActivity extends AppCompatActivity {
    private ImageView mIvBack;
    private ListView mLvAttList;

    private AttendanceAdapter attendanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_record);
        initView();

        attendanceAdapter=new AttendanceAdapter(this);
        mLvAttList.setAdapter(attendanceAdapter);
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mLvAttList = (ListView) findViewById(R.id.lv_att_list);

    }

}
