package com.copticstream.copticstream;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
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


        Log.i(TAG, android.os.Build.SERIAL);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                ("http://copticstream.com/streams.asmx/StreamList", new Response.Listener<JSONArray>() {

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


                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.custom_toast,
                                (ViewGroup) findViewById(R.id.custom_toast_layout));

                        TextView text = (TextView) layout.findViewById(R.id.textToShow);
                        // Set the Text to show in TextView
                        text.setText("Register your church Live stream  - قم بتسجيل البث المباشر لكنيستك http://copticstream.com");

                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.BOTTOM,0,170);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();



                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());

                    }
                });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);


        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(Color.WHITE);


        SearchView.OnQueryTextListener query = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ListViewVideo.adapter.filter(s);

                return false;
            }
        };

        searchView.setOnQueryTextListener(query);


        return true;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //This will make the Action bar and the Tabs never overlap when set to false
        new setHasEmbeddedTabs(actionBar, false);
    }




}
