package com.tooliveapp.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.tooliveapp.globals.JsonGetFields;

public class Feed 
{
	private int feedID;
	private int venueID;
	private String comment;
	private Date createdAt;
	private String venueImagePath;
	private String venueName;
	
	public Feed(int feedID, int venueID, String comment, Date createdAt,
			String venueImagePath, String venueName) {
		super();
		this.feedID = feedID;
		this.venueID = venueID;
		this.comment = comment;
		this.createdAt = createdAt;
		this.venueImagePath = venueImagePath;
		this.venueName = venueName;
	}
	
	public int getFeedID() {
		return feedID;
	}

	public int getVenueID() {
		return venueID;
	}

	public String getComment() {
		return comment;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getVenueImagePath() {
		return venueImagePath;
	}

	public String getVenueName() {
		return venueName;
	}

	public static Feed decodeJSON(JSONObject obj)
	{
		try {		
			int feedID = obj.getInt(JsonGetFields.Feed.FEED_ID);
			int venueID = obj.getInt(JsonGetFields.Feed.VENUE_ID);
			String comment = obj.getString(JsonGetFields.Feed.COMMENT);
			String createdAt = obj.getString(JsonGetFields.Feed.CREATED_AT);
			String name = obj.getString(JsonGetFields.Feed.VENUE_NAME);
			String imagePath = obj.getString(JsonGetFields.Feed.VENUE_IMAGE_PATH);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			
			return new Feed(feedID, venueID, comment, dateFormat.parse(createdAt), imagePath, name);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
