package com.example.peisp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;

public class LiveActivity extends AppCompatActivity {

    private ImageView mIvBack;
    private VideoView mVideo;
    private TextView mTitle;
    private ImageView mTitleDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mVideo = (VideoView) findViewById(R.id.mVideo);
        mTitle = (TextView) findViewById(R.id.title_tv);
        mTitleDown = (ImageView) findViewById(R.id.title_down);
        Uri rawUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);
        mVideo.setVideoURI(rawUri);
        mVideo.start();
        mTitle.setText("西一厂-车间B");
        mTitleDown.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
