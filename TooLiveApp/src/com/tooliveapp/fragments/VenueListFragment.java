package com.tooliveapp.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tooliveapp.MainActivity;
import com.tooliveapp.R;
import com.tooliveapp.globals.Constants;
import com.tooliveapp.globals.JsonGetFields;
import com.tooliveapp.globals.JsonPostFields;
import com.tooliveapp.globals.Routing;
import com.tooliveapp.http.HttpManager;
import com.tooliveapp.http.HttpManager.Request;
import com.tooliveapp.models.Category;
import com.tooliveapp.models.Venue;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class VenueListFragment extends Fragment
{
	private ListView myListView;
	
	public static VenueListFragment newInstance(int categoryID) {
		VenueListFragment fragment = new VenueListFragment();
		
		Bundle args = new Bundle();
        args.putInt(Constants.VENUE_LIST_CATEGORY_ID, categoryID);
        fragment.setArguments(args);
        
        return fragment;
    }
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_venue_list, container, false);
        
        myListView = (ListView)rootView.findViewById(R.id.venue_list_list_view);
        
        myListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	FragmentTransaction trans = getFragmentManager()
						.beginTransaction();
            	
            	Venue model = (Venue) myListView.getItemAtPosition(position);
            	
            	trans.replace(R.id.container, EventsFragment.newInstance(model));
            	trans.addToBackStack(null);
            	trans.commit();
            }
          });
        
        String catID = "/" + getArguments().getInt(Constants.VENUE_LIST_CATEGORY_ID); 
        final String url = Routing.SERVER_URL + Routing.CATEGORY + catID;
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(JsonPostFields.Category.LATITUDE, "0"));
        params.add(new BasicNameValuePair(JsonPostFields.Category.LONGITUDE, "0"));
        params.add(new BasicNameValuePair(JsonPostFields.Category.DISTANCE, "25"));
        
    	
        HttpTaskGet task = new HttpTaskGet();
        task.execute(url, params);
        
        
        return rootView;
    }
	
	private class HttpTaskGet extends AsyncTask<Object, String, String>
	{
		@SuppressWarnings("unchecked")
		@Override
		protected String doInBackground(Object... params) {
			return HttpManager.getContent((String)params[0], Request.POST, (List<NameValuePair>)params[1]);
		}	
		
		@Override
		protected void onPostExecute(String result) 
		{
			try {
				List<Venue> venues = new ArrayList<Venue>();
				JSONObject obj = new JSONObject(result);
				
				boolean error = obj.getBoolean(JsonGetFields.ERROR);
				
				if(!error)
				{
					JSONArray jArray = obj.getJSONArray(JsonGetFields.Venues.VENUES_ARRAY);
				
					for(int count = 0; count < jArray.length(); count++)
					{
						obj = jArray.getJSONObject(count);
						venues.add(Venue.decodeJSON(obj));
					}
					
					myListView.setAdapter(new ArrayAdapter<Venue>(getActivity(), android.R.layout.simple_list_item_1, venues));
				}
			} 
			catch (JSONException e) {}
		}
	}
}
