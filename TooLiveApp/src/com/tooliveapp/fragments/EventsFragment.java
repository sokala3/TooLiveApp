package com.tooliveapp.fragments;

import com.astuetz.PagerSlidingTabStrip;
import com.tooliveapp.MainActivity;
import com.tooliveapp.R;
import com.tooliveapp.MainActivity.PlaceholderFragment;
import com.tooliveapp.adapters.EventPagerAdapter;
import com.tooliveapp.globals.Constants;
import com.tooliveapp.models.Venue;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

public class EventsFragment extends Fragment 
{
	 public static EventsFragment newInstance(Venue model) {
		 EventsFragment fragment = new EventsFragment();
         Bundle args = new Bundle();
         args.putSerializable(Constants.EVENT_VENUE, model);
         fragment.setArguments(args);
         return fragment;
     }
	 
	 private Venue venue;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    setHasOptionsMenu(true);
	    super.onCreate(savedInstanceState);
	    
	    this.venue = (Venue) getArguments().getSerializable(Constants.EVENT_VENUE);
	}
	
	@Override
    public void onPrepareOptionsMenu(Menu menu) 
	{
        menu.findItem(R.id.action_venue_name).setTitle(venue.getName());
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
        if (id == R.id.action_venue_name) 
        {
        	showPopup(getView());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	private void showPopup(View anchorView)
	{
		LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View popupView = inflater.inflate(R.layout.venue_information_layout, null); 
	    final PopupWindow popupWindow = new PopupWindow(popupView, 
	                           LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

	    // If the PopupWindow should be focusable
	    popupWindow.setFocusable(true);
	    
	    // If you need the PopupWindow to dismiss when when touched outside 
	    popupWindow.setBackgroundDrawable(new ColorDrawable());
	    
	    // Using location, the PopupWindow will be displayed right under anchorView
	    popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
	    
	    TextView venueName = (TextView)popupView.findViewById(R.id.venue_info_name);
	    TextView venueAddress = (TextView)popupView.findViewById(R.id.venue_info_address);
	    TextView venueEmail = (TextView)popupView.findViewById(R.id.venue_info_email);
	    TextView venuePhone = (TextView)popupView.findViewById(R.id.venue_info_phone);
	    TextView venueWebsite = (TextView)popupView.findViewById(R.id.venue_info_website);
	    
	    TextView close = (TextView)popupView.findViewById(R.id.venue_close);
	    close.setOnClickListener(new Button.OnClickListener()
	    {

			@Override
			public void onClick(View arg0) {
				popupWindow.dismiss();
				
			}
    		
	    });
	    
	    venueName.setText(venue.getName());
	    venueAddress.setText(venue.getLocation());
	    venueEmail.setText(venue.getEmail());
	    venuePhone.setText(venue.getPhone());
	    venueWebsite.setText(venue.getWebsite());
	}
}
