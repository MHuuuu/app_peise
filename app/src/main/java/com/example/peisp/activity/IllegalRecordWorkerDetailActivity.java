package com.example.peisp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class IllegalRecordWorkerDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvBack;
    private RelativeLayout mBtnCheck;
    private ImageView mIvIllPic;
    private ArrayAdapter<String> adapter;

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
        String[] dates = {"场内警报提醒", "接通场内麦克风", "标记为已处理"};
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dates
                );
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check:
                SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("向文波")
                    .setContentText("未佩戴安全帽！")
                    .setConfirmText("发送警报提醒")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            // reuse previous dialog instance
                            sDialog
                                    .setConfirmClickListener(null)
                                    .setContentText("警报已发送！")
                                    .showCancelButton(false)
                                    .setConfirmText("取消")
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        }
                    });
                pDialog.setCancelable(true);
                pDialog.showCancelButton(true);
                pDialog.setCancelText("标记为已处理");
                pDialog.changeAlertType(SweetAlertDialog.WARNING_TYPE);
                pDialog.show();
                break;
        }
    }
}
