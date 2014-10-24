package com.copticstream.copticstream;

import android.app.ActionBar;
import android.app.ProgressDialog;
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

public class MainActivity extends FragmentActivity implements Constant {

    public static final String TAG = "MainActivity";
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private ProgressDialog dialog;

    // Tab titles
    private String[] tabs = {"Video Stream", "Audio Stream"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        dialog = new ProgressDialog(this);
        dialog.setMessage(LOADING_MESSAGE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                ("http://copticstream.com/streams.asmx/StreamListByType", new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        List<Stream> streamListVideo = new ArrayList<Stream>();


                        List<Fragment> fragments = new ArrayList<Fragment>();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Gson gson = new Gson();
                                Stream stream = gson.fromJson(String.valueOf(jsonObject), Stream.class);

                                    streamListVideo.add(stream);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        fragments.add(new ListViewVideo("streamListVideo", streamListVideo));
                        mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), fragments);
                        viewPager.setAdapter(mAdapter);
                        dialog.hide();


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);




    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //This will make the Action bar and the Tabs never overlap when set to false
        new setHasEmbeddedTabs(actionBar, false);
    }




}
