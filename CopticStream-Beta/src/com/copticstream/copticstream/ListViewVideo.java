package com.copticstream.copticstream;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListViewVideo extends Fragment  {

    private final String TAG = "ListViewVideo";
    private String target;
    public static StreamListBaseAdapter adapter;
    private List<Stream> streamList;
    private ProgressDialog dialog;

    public ListViewVideo(String target, List<Stream> streamList) {
        this.target = target;
        this.streamList = streamList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview_video, container, false);
        ListView listView = (ListView) view.findViewById(R.id.videoList);

        adapter = new StreamListBaseAdapter(getActivity(), streamList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.streamURL);

                //Get the stream Type ID

                TextView streamTypeID = (TextView) view.findViewById(R.id.streamTypeID);
                int typeID  = Integer.parseInt((String) streamTypeID.getText());


                //Increment the Stream view

                TextView streamID = (TextView) view.findViewById(R.id.streamID);
                int ID  = Integer.parseInt((String) streamID.getText());

                new StreamViews(getActivity()).IncrementStreamViews(ID);



                //RTSP
                if(typeID == 1){
                    Intent intent = new Intent(getActivity(), RTSPMediaPlayer.class);
                    intent.putExtra("streamURL", textView.getText());
                    startActivity(intent);
                   return;
                }

                //RTMP
                if(typeID == 2){
                    Intent intent = new Intent(getActivity(), MediaPlayerVideo.class);
                    intent.putExtra("streamURL", textView.getText());
                    startActivity(intent);
                    return;
                }

                //YouTube
                if(typeID == 3){
                    Intent intent = new Intent(getActivity(), YouTubeMediaPlayer.class);
                    String streamURL = (String) textView.getText();
                    String YTID = extractYTId(streamURL);
                    intent.putExtra("YTID", YTID);
                    startActivity(intent);
                    return;
                }

                Log.i(TAG, (String) textView.getText());

            }
        });

        return view;

    }
    public static String extractYTId(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(".*(?:youtu.be\\/|v\\/|u\\/\\w\\/|embed\\/|watch\\?v=)([^#\\&\\?]*).*");
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()){
            vId = matcher.group(1);
        }
        return vId;
    }

}
