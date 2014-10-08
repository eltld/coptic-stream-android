package com.copticstream.copticstream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mina on 10/4/2014.
 */
public class StreamListBaseAdapter extends BaseAdapter {
    List<Stream> streams;
    Context context;
    LayoutInflater inflater;
    public StreamListBaseAdapter(Context context, List<Stream> streams) {
        super();
        this.streams = streams;
        this.context = context;
        this.inflater = inflater.from(this.context);
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

        ViewHolder viewHolder;

        if(convertView == null){
            //What View should i be -this-> layout_list_item
            convertView = inflater.inflate(R.layout.layout_list_item,null);
            //What items should i fill
            viewHolder = new ViewHolder();
            convertView.setTag(viewGroup);


        }else{
            //if converted view reset your view from the view Holder to change their values
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //fill view holder with value

        //fill The text view in the convert view which is layout_list_item now
        TextView textView = (TextView) convertView.findViewById(R.id.title);
        textView.setText(streams.get(position).getstreamName());
        viewHolder.title = textView;

        TextView textView1 = (TextView) convertView.findViewById(R.id.description);
        textView1.setText(streams.get(position).getstreamDescription());
        viewHolder.description = textView1;

        ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);
        imageView.setImageResource(R.drawable.cross);
        viewHolder.imageView = imageView;

        return convertView;
    }


    public class ViewHolder{
        ImageView imageView;
        TextView title, description;

    }
}
