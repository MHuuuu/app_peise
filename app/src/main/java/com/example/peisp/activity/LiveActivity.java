package com.example.peisp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peisp.R;

public class LiveActivity extends AppCompatActivity {
    private VideoView mVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mVideo = (VideoView)findViewById(R.id.mVideo);
        Uri rawUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);
        mVideo.setVideoURI(rawUri);
        mVideo.start();
    }
}
