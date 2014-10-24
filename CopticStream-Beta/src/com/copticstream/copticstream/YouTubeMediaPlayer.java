package com.copticstream.copticstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Mina on 10/17/2014.
 */
public class YouTubeMediaPlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, Constant {

    public static final String API_KEY = Constant.API_KEY;
    public Intent intent;
    public static  String YTID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_videoplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        intent = getIntent();
        YTID = intent.getStringExtra("YTID").toString();


        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** Start buffering **/
        if (!wasRestored) {
            player.loadVideo(YTID);
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        finish();
    }
}