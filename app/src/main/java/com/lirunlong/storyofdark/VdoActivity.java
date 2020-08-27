package com.lirunlong.storyofdark;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class VdoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //如果上面的不起作用，可以换成下面的。
        if (getSupportActionBar()!=null) getSupportActionBar().hide();
        if (getActionBar()!=null) getActionBar().hide();
        setContentView(R.layout.activity_vdo);

        Log.i("lee","this is second view");
        String path = Environment.getExternalStorageDirectory().getPath();
        Log.i("lee", path);
//        String vPath = "file:///android_asset/v.mp4";
//        String vPath = "content://com.lirunlong.storyofdark/v.mp4";
        String vPath = "android.resource://" + "com.lirunlong.storyofdark" + "/" + R.raw.v;

//        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/Test_Movie.m4v");
        Uri uri = Uri.parse(vPath);
        VideoView videoView = (VideoView)(findViewById(R.id.videoView_dark));
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
//        videoView.setVideoPath(vPath);
        videoView.start();
        videoView.requestFocus();

    }
}