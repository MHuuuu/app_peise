package com.example.peisp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.dou361.ijkplayer.widget.AndroidMediaController;
import com.dou361.ijkplayer.widget.IjkVideoView;
import com.example.peisp.R;
import com.fengmap.android.map.FMMap;
import com.fengmap.android.map.FMMapView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LiveActivity extends AppCompatActivity {

    private ImageView mIvBack;
    private IjkVideoView  mVideo;
    private TextView mTitle;
    private ImageView mTitleDown;

    private FMMap mFMMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_live);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mVideo = (IjkVideoView) findViewById(R.id.mVideo);
        mTitle = (TextView) findViewById(R.id.title_tv);
        mTitleDown = (ImageView) findViewById(R.id.title_down);
//        Uri rawUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);
        FMMapView mapView = (FMMapView) findViewById(R.id.mapView);
        mFMMap = mapView.getFMMap();       //获取地图操作对象
        String bid = "1295196521971658754";             //地图id
//        String bid = "10347";
        mFMMap.openMapById(bid, true);          //打开地图


        initPlayer();
        mTitle.setText("西一厂-车间B");
        mTitleDown.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initPlayer() {
        //初始化播放库
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        IjkMediaPlayer ijkMediaPlayer = null;
        ijkMediaPlayer = new IjkMediaPlayer();


        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);

        // 黑屏
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec_mpeg4", 1);
        // 降低 播放 rtmp 播放的延迟
        ijkMediaPlayer.setOption(1, "analyzemaxduration", 100L);
        ijkMediaPlayer.setOption(1, "probesize", 10240L);
        ijkMediaPlayer.setOption(1, "flush_packets", 1L);
        ijkMediaPlayer.setOption(4, "packet-buffering", 0L);
        //丢帧
        ijkMediaPlayer.setOption(4, "framedrop", 1L);

        //硬解码造成黑屏无声
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "soundtouch", 1);

        // 清空DNS，因为DNS的问题报10000
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "dns_cache_clear", 1);

        //使用AndroidMediaController类控制播放相关操作
        AndroidMediaController mMediaController = new AndroidMediaController(this, false);
        //mMediaController.setSupportActionBar(actionBar);
        mVideo.setMediaController(mMediaController);
        // 香港财经  rtmp://202.69.69.180:443/webcast/bshdlive-pc
        //设置要播放的直播或者视频的地址：
        mVideo.setVideoPath("rtmp://202.69.69.180:443/webcast/bshdlive-pc");
        //开始播放
        mVideo.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mVideo.isPlaying()) {
            mVideo.stopPlayback();
            mVideo.release(true);
        }
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    public void onBackPressed() {
        if (mFMMap != null) {
            mFMMap.onDestroy();
        }
        super.onBackPressed();
    }
}
