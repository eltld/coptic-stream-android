package com.copticstream.copticstream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.vov.vitamio.utils.Log;

/**
 * Created by Mina on 10/4/2014.
 */
public class StreamListBaseAdapter extends BaseAdapter {
    List<Stream> streams;
    List<Stream> filter_streams ;
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;

    public StreamListBaseAdapter(Context context, List<Stream> streams) {
        super();
        this.streams = streams;
        this.context = context;
        this.inflater = inflater.from(this.context);
        this.imageLoader = VolleySingleton.getInstance(this.context).getImageLoader();
        filter_streams = new ArrayList<Stream>();
        this.filter_streams.addAll(streams);
    }

    @Override
    public int getCount() {
        return streams.size();
    }

    @Override
    public Object getItem(int position) {
        return streams.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if (imageLoader == null)
            imageLoader = VolleySingleton.getInstance(context).getImageLoader();


        //What View should i be -this-> layout_list_item
        convertView = inflater.inflate(R.layout.layout_list_item, null);
        convertView.setTag(viewGroup);


        //fill The text view in the convert view which is layout_list_item now
        TextView textView = (TextView) convertView.findViewById(R.id.title);
        textView.setText(streams.get(position).getstreamName());

        TextView textView1 = (TextView) convertView.findViewById(R.id.description);
        textView1.setText(streams.get(position).getstreamDescription());


        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        thumbNail.setImageUrl(streams.get(position).getstreamImagethumbnail(), imageLoader);

        TextView streamURL = (TextView) convertView.findViewById(R.id.streamURL);
        streamURL.setText(streams.get(position).getstreamURL());

        TextView streamTypeID = (TextView) convertView.findViewById(R.id.streamTypeID);
        streamTypeID.setText(streams.get(position).getstreamTypeID().toString());

        TextView streamID = (TextView) convertView.findViewById(R.id.streamID);
        streamID.setText(String.valueOf(streams.get(position).getstreamID()));


        return convertView;
    }

    public void filter(String strFilter){
       Log.i("hello",strFilter + "mina");
        streams.clear();
        if(strFilter.length() == 0){
            streams.addAll(filter_streams);
        }
        else {
            for (Stream stream : filter_streams ){
                if(stream.getstreamName().toLowerCase(Locale.getDefault()).contains(strFilter.toLowerCase(Locale.getDefault()))){
                    streams.add(stream);
                }
            }
        }
        notifyDataSetChanged();
    }


}
