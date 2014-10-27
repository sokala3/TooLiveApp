package com.tooliveapp.fragments;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

import com.tooliveapp.MainActivity;
import com.tooliveapp.R;
import com.tooliveapp.globals.Constants;
import com.tooliveapp.globals.JsonGetFields;
import com.tooliveapp.globals.Routing;
import com.tooliveapp.http.HttpManager;
import com.tooliveapp.http.HttpManager.Request;
import com.tooliveapp.models.Category;

public class CategoriesFragment extends Fragment
{
	
	private ListView myListView;
	
	public static CategoriesFragment newInstance(int sectionNum) {
		CategoriesFragment fragment = new CategoriesFragment();
		
		Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNum);
        fragment.setArguments(args);
        
        return fragment;
    }
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        
        myListView = (ListView)rootView.findViewById(R.id.category_list_view);
        
        myListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	FragmentTransaction trans = getFragmentManager()
						.beginTransaction();
            	
            	Category model = (Category) myListView.getItemAtPosition(position);
            	
            	trans.replace(R.id.container, VenueListFragment.newInstance(model.getCategoryID()));
            	trans.addToBackStack(null);
            	trans.commit();
            }
          });
        
        final String url = Routing.SERVER_URL + Routing.CATEGORY;
    	
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
				List<Category> categories = new ArrayList<Category>();
				JSONObject obj = new JSONObject(result);
				
				boolean error = obj.getBoolean(JsonGetFields.ERROR);
				
				if(!error)
				{
					JSONArray jArray = obj.getJSONArray(JsonGetFields.Category.CATEGORIES_ARRAY);
				
					for(int count = 0; count < jArray.length(); count++)
					{
						obj = jArray.getJSONObject(count);
						categories.add(Category.decodeJSON(obj));
					}
					
					myListView.setAdapter(new ArrayAdapter<Category>(getActivity(), android.R.layout.simple_list_item_1, categories));
				}
			} 
			catch (JSONException e) {}
		}
	}

}
