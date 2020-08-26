package com.example.peisp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dou361.ijkplayer.widget.AndroidMediaController;
import com.dou361.ijkplayer.widget.IjkVideoView;
import com.example.peisp.R;
import com.fengmap.android.map.FMMap;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LiveActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG="手机控制台";
    private static String AUTO_MODE = "自动巡航模式";    //默认模式
    private static String ARTIFICIAL_MODE = "人工审查模式";
    private static String TURN_FONT = "a";
    private static String TURN_BACK = "b";
    private static String START = "c";
    private static String STOP = "d";
    private static String TURN_ON_AUTO = "e";
    private static String TURN_OFF_AUTO = "f";
    private static String POST_URL = "http://59.110.70.93:8080/sendMSG";
    private static String MAP_URL = "http://59.110.70.93:8080/FMMapBasic.html";



    private ImageView mIvBack;
    private IjkVideoView mVideo;
    private TextView mTitle;
    private ImageView mTitleDown;

    private WebView webView;
    private FMMap mFMMap;

    private TextView tvHandle;
    private ImageButton btnSwitch;
    private ImageButton btnLeft;
    private ImageButton btnRunOrStop;
    private ImageButton btnRight;

    private MediaType mediaType;
    private boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        initView();

        mediaType = MediaType.parse("text/plain ; charset=utf-8");
        //        Uri rawUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);

/*
        FMMapView mapView = (FMMapView) findViewById(R.id.mapView);
        mFMMap = mapView.getFMMap();       //获取地图操作对象
        String bid = "1295196521971658754";             //地图id
//        String bid = "10347";
        mFMMap.openMapById(bid, true);          //打开地图
*/

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(MAP_URL);

        initPlayer();
        mTitle.setText("西一厂-车间B");
        mTitleDown.setVisibility(View.VISIBLE);


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

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mVideo = (IjkVideoView) findViewById(R.id.mVideo);
        mTitle = (TextView) findViewById(R.id.title_tv);
        mTitleDown = (ImageView) findViewById(R.id.title_down);
        mIvBack.setOnClickListener(this);
        tvHandle = (TextView) findViewById(R.id.tv_handle);
        tvHandle.setOnClickListener(this);
        btnSwitch = (ImageButton) findViewById(R.id.btn_switch);
        btnSwitch.setOnClickListener(this);
        btnLeft = (ImageButton) findViewById(R.id.btn_left);
        btnLeft.setOnClickListener(this);
        btnRunOrStop = (ImageButton) findViewById(R.id.btn_run_or_stop);
        btnRunOrStop.setOnClickListener(this);
        btnRight = (ImageButton) findViewById(R.id.btn_right);
        btnRight.setOnClickListener(this);

        webView = (WebView) findViewById(R.id.web_view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
            case R.id.btn_switch:
                boolean isAuto=tvHandle.getText().equals(AUTO_MODE);
                btnLeft.setEnabled(isAuto);
                btnRunOrStop.setEnabled(isAuto);
                btnRight.setEnabled(isAuto);
                tvHandle.setText(isAuto?ARTIFICIAL_MODE:AUTO_MODE);
                sendMSG(isAuto?TURN_OFF_AUTO:TURN_ON_AUTO);
                break;
            case R.id.btn_left:
                sendMSG(TURN_BACK);
                break;
            case R.id.btn_run_or_stop:
                isStart=!isStart;
                btnRunOrStop.setBackgroundResource(isStart?R.mipmap.pause_outline_b:R.mipmap.play_outline_b);
                sendMSG(isStart?START:STOP);
                break;
            case R.id.btn_right:
                sendMSG(TURN_FONT);
                break;
        }
    }

    private void sendMSG(String sign){
        Request request = new Request.Builder()
                .url(POST_URL)
                .post(RequestBody.create(mediaType, sign))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
//                }
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

}
