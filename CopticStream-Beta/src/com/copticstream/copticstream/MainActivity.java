package com.copticstream.copticstream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		JsonArrayRequest jsArrayRequest = new JsonArrayRequest("http://copticstream.com/streams.asmx/StreamListByType",
				new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				try {
					for(int i =0;i<response.length();i++){
						JSONObject object = response.getJSONObject(i);
						
						Log.i("Json",object.getString("streamName"));
						
					}


				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		});
		
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(
				Request.Method.GET,
				"http://copticstream.com/streams.asmx/HelloWorld", null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							Log.i("Response: ", response.getString("Message"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
		
		// Access the RequestQueue through your singleton class.
		MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
		MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
		
	}
	
	public void playVideo(View view) {
		Intent intent = new Intent(this,MediaPlayerVideo.class);
		intent.putExtra("streamURL", "rtmp://aljazeeraflashlivefs.fplive.net/aljazeeraflashlive-live/aljazeera_eng_high");
		intent.putExtra("stream", 5);
		startActivity(intent);
		
	}

}
