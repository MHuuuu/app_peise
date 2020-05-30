package com.example.peisp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;

public class IllegalRecordWorkerDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvBack;
    private RelativeLayout mBtnCheck;
    private ImageView mIvIllPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegal_record_worker_detail);
        initView();
        initData();

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(IllegalRecordWorkerDetailActivity.this, IllegalRecordWorkerActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mBtnCheck = (RelativeLayout) findViewById(R.id.btn_check);
        mIvIllPic = (ImageView) findViewById(R.id.iv_ill_pic);
        mBtnCheck.setOnClickListener(this);
        mIvIllPic.setOnClickListener(this);
    }

    private void initData() {
    }


    @Override
    public void onClick(View view) {

    }
}
