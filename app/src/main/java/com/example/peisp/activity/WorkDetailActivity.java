package com.example.peisp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;

public class WorkDetailActivity extends AppCompatActivity {
    private ImageView mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_detail);
        initView();
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
    }
}
