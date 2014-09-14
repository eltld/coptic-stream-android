package com.copticstream.copticstream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void playVideo(View view) {
		Intent intent = new Intent(this,MediaPlayerVideo.class);
		intent.putExtra("streamURL", "rtmp://aljazeeraflashlivefs.fplive.net/aljazeeraflashlive-live/aljazeera_eng_high");
		intent.putExtra("stream", 5);
		startActivity(intent);
		
	}

}
