package com.copticstream.copticstream;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements
        ActionBar.TabListener {

    public static final String TAG = "MainActivity";
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

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                ("http://copticstream.com/streams.asmx/StreamListByType", new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        List<Stream> streamListVideo = new ArrayList<Stream>();
                        List<Stream> streamListAudio = new ArrayList<Stream>();


                        List<Fragment> fragments = new ArrayList<Fragment>();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Gson gson = new Gson();
                                Stream stream = gson.fromJson(String.valueOf(jsonObject), Stream.class);

                                if (stream.getstreamTypeID() == 1) {
                                    streamListVideo.add(stream);
                                } else {
                                    streamListAudio.add(stream);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        fragments.add(new ListViewVideo("streamListVideo", streamListVideo));
                        fragments.add(new ListViewAudio());

                        mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), fragments);

                        viewPager.setAdapter(mAdapter);


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);


        //This will make the Action bar and the Tabs never overlap when set to false
        new setHasEmbeddedTabs(actionBar, false);

        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setIcon(R.drawable.video_file).setTabListener(this));
        }


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


    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //This will make the Action bar and the Tabs never overlap when set to false
        new setHasEmbeddedTabs(actionBar, false);

//        // Checks the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
//        }
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




}
