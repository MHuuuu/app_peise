package com.example.peisp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.peisp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mIvInfo;
    private CardView mBtnLive;
    private CardView mBtnRecard;
    private CardView mBtnAttendance;
    private CardView mBtnManageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mIvInfo.setOnClickListener(this);
        mBtnLive.setOnClickListener(this);
        mBtnRecard.setOnClickListener(this);
        mBtnAttendance.setOnClickListener(this);
        mBtnManageList.setOnClickListener(this);
    }

    private void initView() {
        mIvInfo = (ImageView) findViewById(R.id.iv_info);
        mBtnLive = (CardView) findViewById(R.id.btn_live);
        mBtnRecard = (CardView) findViewById(R.id.btn_recard);
        mBtnAttendance = (CardView) findViewById(R.id.btn_attendance);
        mBtnManageList = (CardView) findViewById(R.id.btn_manage_list);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
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
