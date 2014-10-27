package com.tooliveapp.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.tooliveapp.MainActivity;
import com.tooliveapp.R;
import com.tooliveapp.adapters.FeedAdapter;
import com.tooliveapp.globals.Constants;
import com.tooliveapp.globals.JsonGetFields;
import com.tooliveapp.globals.Routing;
import com.tooliveapp.http.HttpManager;
import com.tooliveapp.http.HttpManager.Request;
import com.tooliveapp.models.Feed;

public class FeedFragment extends Fragment
{
	private ListView myListView;
	ArrayAdapter<Feed> adapter;
	
	public static FeedFragment newInstance(int sectionID) {
		FeedFragment fragment = new FeedFragment();
		
		Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionID);
        fragment.setArguments(args);
        
        return fragment;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        myListView = (ListView)rootView.findViewById(R.id.feed_list_view);
        
        final String url = Routing.SERVER_URL + Routing.FEED;
    	
        HttpTaskGet task = new HttpTaskGet();
        task.execute(url);
        
        
        return rootView;
    }
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }
	
	private class HttpTaskGet extends AsyncTask<String, String, String>
	{
		@Override
		protected String doInBackground(String... params) {
			return HttpManager.getContent((String)params[0], Request.GET);
		}	
		
		@Override
		protected void onPostExecute(String result)
		{
			try {
				List<Feed> feeds = new ArrayList<Feed>();
				JSONObject obj = new JSONObject(result);
				
				boolean error = obj.getBoolean(JsonGetFields.ERROR);
				
				if(!error)
				{
					JSONArray jArray = obj.getJSONArray(JsonGetFields.Feed.FEED_ARRAY);
				
					for(int count = 0; count < jArray.length(); count++)
					{
						obj = jArray.getJSONObject(count);
						feeds.add(Feed.decodeJSON(obj));
					}
					
					adapter = new FeedAdapter(getActivity(), android.R.layout.simple_list_item_1, feeds);
					myListView.setAdapter(adapter);
				}
			} 
			catch (JSONException e) {}
		}
	}
}
