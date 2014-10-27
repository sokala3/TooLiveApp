package com.tooliveapp.adapters;

import java.util.List;
import java.util.Locale;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tooliveapp.R;
import com.tooliveapp.models.Feed;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FeedAdapter extends ArrayAdapter<Feed>
{
	LayoutInflater inflater;
    public FeedAdapter(Context context, int layoutResourceId, List<Feed> data) {
        super(context, layoutResourceId, data);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Feed item = null;
        ViewHolder holder = null;
        
        if(convertView == null)
        {
        	convertView = inflater.inflate(R.layout.feed_listview_layout, parent, false);
        	holder = new ViewHolder();
        	
        	holder.imageView = (ImageView)convertView.findViewById(R.id.feed_image);
        	holder.comment = (TextView) convertView.findViewById(R.id.feed_comment);
        	
        	convertView.setTag(holder);
        }
        else
        {
        	holder = (ViewHolder)convertView.getTag();
        }
            
        item = getItem(position);
   
        ImageLoader.getInstance().displayImage(item.getVenueImagePath(), holder.imageView);
        holder.comment.setText(item.getComment());

        return convertView;
    }
    
    private class ViewHolder
    {
    	ImageView imageView;
        TextView comment;
    }
}
