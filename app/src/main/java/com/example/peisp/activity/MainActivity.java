package com.example.peisp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvInfo;
    private RelativeLayout mBtnLive;
    private RelativeLayout mBtnRecard;
    private RelativeLayout mBtnAttendance;
    private RelativeLayout mBtnManageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mIvInfo = (ImageView) findViewById(R.id.iv_info);
        mIvInfo.setOnClickListener(this);
        mBtnLive = (RelativeLayout) findViewById(R.id.btn_live);
        mBtnLive.setOnClickListener(this);
        mBtnRecard = (RelativeLayout) findViewById(R.id.btn_recard);
        mBtnRecard.setOnClickListener(this);
        mBtnAttendance = (RelativeLayout) findViewById(R.id.btn_attendance);
        mBtnAttendance.setOnClickListener(this);
        mBtnManageList = (RelativeLayout) findViewById(R.id.btn_manage_list);
        mBtnManageList.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_info:
                intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_live:
                intent = new Intent(MainActivity.this, LiveActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recard:
                intent = new Intent(MainActivity.this, IllegalRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_attendance:
                intent = new Intent(MainActivity.this, AttendanceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_manage_list:
                intent = new Intent(MainActivity.this, ManageMainActivity.class);
                startActivity(intent);
                break;

        }
    }
}
