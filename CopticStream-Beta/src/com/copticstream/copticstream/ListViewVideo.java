package com.copticstream.copticstream;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ListViewVideo extends Fragment {

    private final String TAG = "ListViewVideo";
    private String target;
    private List<Stream> streamList;

    public ListViewVideo(String target, List<Stream> streamList) {
        this.target = target;
        this.streamList = streamList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview_video, container, false);
        ListView listView = (ListView) view.findViewById(R.id.videoList);


        listView.setAdapter(new StreamListBaseAdapter(getActivity(), streamList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.title);

                Log.i(TAG, (String) textView.getText());

            }
        });

        return view;

    }
}
