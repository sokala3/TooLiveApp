package com.tooliveapp.adapters;
import com.tooliveapp.R;
import com.tooliveapp.globals.Constants;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventPagerAdapter extends FragmentPagerAdapter 
{ 
	  private final String[] TITLES = { "Events", "Media", "Chatter", "Reviews" };
	
	  public EventPagerAdapter(Context context, FragmentManager mgr) 
	  {
	    super(mgr);
	  }
	
	  @Override
	  public int getCount() 
	  {
	    return(TITLES.length);
	  }
	
	  @Override
	  public Fragment getItem(int position) 
	  {
	    return PlaceholderFragment.newInstance(position);
	  }
	
	  @Override
	  public String getPageTitle(int position) 
	  {
	    return TITLES[position];
	  }
	  
	  /**
	     * A placeholder fragment containing a simple view.
	     */
	    private static class PlaceholderFragment extends Fragment {
	        /**
	         * Returns a new instance of this fragment for the given section
	         * number.
	         */
	        public static PlaceholderFragment newInstance(int sectionNumber) {
	            PlaceholderFragment fragment = new PlaceholderFragment();
	            Bundle args = new Bundle();
	            args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
	            fragment.setArguments(args);
	            return fragment;
	        }

	        public PlaceholderFragment() {
	        }

	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
	            return rootView;
	        }
	    }
}
