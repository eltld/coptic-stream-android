package com.copticstream.copticstream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListViewAudio extends Fragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.listview_audio, container, false);
         TextView tv = (TextView) rootView.findViewById(R.id.textView1);
         tv.setText("Hello World From Text");
        return rootView;
    }
}
