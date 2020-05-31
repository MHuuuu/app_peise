package com.example.peisp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;

import java.time.Instant;

public class InfoActivity extends AppCompatActivity {
    private ImageView mIvBack;
    private TextView mTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mTitleTv.setText("设置");
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTitleTv = (TextView) findViewById(R.id.title_tv);
    }
}
