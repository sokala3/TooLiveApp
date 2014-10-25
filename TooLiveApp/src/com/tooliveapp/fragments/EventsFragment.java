package com.tooliveapp.fragments;

import com.astuetz.PagerSlidingTabStrip;
import com.tooliveapp.MainActivity;
import com.tooliveapp.R;
import com.tooliveapp.MainActivity.PlaceholderFragment;
import com.tooliveapp.adapters.EventPagerAdapter;
import com.tooliveapp.globals.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class EventsFragment extends Fragment 
{
	 public static EventsFragment newInstance(int sectionNumber) {
		 EventsFragment fragment = new EventsFragment();
         Bundle args = new Bundle();
         args.putInt(Constants.ARG_SECTION_NUMBER, 2);
         fragment.setArguments(args);
         return fragment;
     }
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    setHasOptionsMenu(true);
	    super.onCreate(savedInstanceState);
	}
	
	@Override
    public void onPrepareOptionsMenu(Menu menu) 
	{
        menu.findItem(R.id.action_venue_name).setTitle("Flip Flops");
        super.onPrepareOptionsMenu(menu);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
	    View result = inflater.inflate(R.layout.fragment_events, container, false);
	    ViewPager pager = (ViewPager)result.findViewById(R.id.pager);

	    pager.setAdapter(new EventPagerAdapter(getActivity(), getChildFragmentManager()));
	    
	    PagerSlidingTabStrip tabs = (PagerSlidingTabStrip)result.findViewById(R.id.tabs);
	    tabs.setViewPager(pager);
	    
	    tabs.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener());

	    return(result);
	  }
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.main, menu);
	    super.onCreateOptionsMenu(menu,inflater);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }
}
