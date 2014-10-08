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

import java.util.ArrayList;
import java.util.List;

public class ListViewVideo extends Fragment {

    final String TAG = "ListViewVideo";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview_video, container, false);
        ListView listView = (ListView) view.findViewById(R.id.videoList);

        List<Stream> streams = new ArrayList<Stream>();
        Stream stream = new Stream();
        stream.setstreamName("Rakoty");
        stream.setstreamDescription("Alexandria Coptic Radio -  كنيسة الشهيد العظيم مارجرجس ");
        stream.setstreamEnabled(true);
        stream.setstreamID(125);
        stream.setstreamImage("Image");
        stream.setstreamImagethumbnail("image thumb");
        stream.setstreamURL("URL");
        stream.setstreamTypeID(12500);

        streams.add(stream);


        listView.setAdapter(new StreamListBaseAdapter(getActivity(), streams));
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
