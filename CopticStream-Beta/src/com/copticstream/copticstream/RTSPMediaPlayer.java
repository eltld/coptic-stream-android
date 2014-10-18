package com.copticstream.copticstream;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Mina on 10/18/2014.
 */
public class RTSPMediaPlayer extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtsp_videoplayer);


        VideoView videoView = (VideoView) findViewById(R.id.RTSPplayer);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);

        Intent intent = getIntent();
        String streamURL = intent.getStringExtra("streamURL");

        Uri video = Uri.parse(streamURL);
        videoView.setMediaController(null);
        videoView.setVideoURI(video);
        videoView.start();
    }


}
