package com.copticstream.copticstream;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements
        ActionBar.TabListener {

    public final String TAG = "MainActivity";
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;

    // Tab titles
    private String[] tabs = {"Video Stream", "Audio Stream"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();






        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setIcon(R.drawable.video_file).setTabListener(this));
        }

        //This will make the Action bar and the Tabs never overlap when set to false
        new setHasEmbeddedTabs(actionBar, false);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // when
            // the
            // page
            // start
            // scrolling
            @Override
            public void onPageSelected(int position) {// When Scroll Completed
                // TODO Auto-generated method stub
                actionBar.setSelectedNavigationItem(position);
                actionBar.setTitle(tabs[position]);
            }

            @Override
            public void onPageScrolled(int position, float arg1,
                                       int arg2) { // When the page completely Changed
                // "Changed Complete"


            }

            @Override
            public void onPageScrollStateChanged(int position) {
                // TODO Auto-generated method stub

            }
        });


        //Fill The ListView Of the video fragmant


        String url = "http://copticstream.com/streams.asmx/StreamListByType";
        JsonArrayRequest jsonArrayRequestRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, response.toString());


                try {
                    DeserializerStreamListFromJson(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                List<Fragment> fragments = new ArrayList<Fragment>();

                fragments.add(new ListViewVideo());
                fragments.add(new ListViewAudio());

                mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), fragments);

                viewPager.setAdapter(mAdapter);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequestRequest);

    }

    public static List<Stream> DeserializerStreamListFromJson(JSONArray jsonArrayStream) throws JSONException {
        List<Stream> streams = new ArrayList<Stream>();

        for(int i = 0; i < jsonArrayStream.length(); i++){
            Stream stream = new Stream();
            JSONObject current = (JSONObject) jsonArrayStream.get(i);
          Log.i("Main",  current.getString("streamName"));

        }

        return streams;
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {// When tab
        // clicked also
        // get called on
        // pages load
        // TODO Auto-generated method stub
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {// When a Tab
        // is
        // unselected
        // TODO Auto-generated method stub
        // Use tab.getposition
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }


    public void viewVideo(View view) {

        Intent intent = new Intent(this, MediaPlayerVideo.class);
        intent.putExtra("streamURL", "rtmp://aljazeeraflashlivefs.fplive.net/aljazeeraflashlive-live/aljazeera_eng_high");
        startActivity(intent);

        Log.i(TAG, "Clicked");
    }


}
