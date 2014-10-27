package com.tooliveapp.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.tooliveapp.globals.JsonGetFields;

public class Venue implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8458607502120804693L;
	private int venueID;
	private int userID;
	private String name;
	private String location;
	private int categoriesID;
	private String imagePath;
	private double rating;
	private String phone;
	private String email;
	private String website;
	private String categoryName;
	
	public Venue(int venueID, int userID, String name, String location,
			int categoriesID, String imagePath, double rating, String phone,
			String email, String website, String categoryName) {
		super();
		this.venueID = venueID;
		this.userID = userID;
		this.name = name;
		this.location = location;
		this.categoriesID = categoriesID;
		this.imagePath = imagePath;
		this.rating = rating;
		this.phone = phone;
		this.email = email;
		this.website = website;
		this.categoryName = categoryName;
	}
	
	public int getVenueID() {
		return venueID;
	}

	public int getUserID() {
		return userID;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public int getCategoriesID() {
		return categoriesID;
	}

	public String getImagePath() {
		return imagePath;
	}

	public double getRating() {
		return rating;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getWebsite() {
		return website;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
	public String toString()
	{
		return this.name;
	}

	public static Venue decodeJSON(JSONObject obj)
	{
		try {		
			int venueID = obj.getInt(JsonGetFields.Venues.VENUE_ID);
			int userID = obj.getInt(JsonGetFields.Venues.USER_ID);
			String name = obj.getString(JsonGetFields.Venues.NAME);
			String location = obj.getString(JsonGetFields.Venues.LOCATION);
			int categoryID = obj.getInt(JsonGetFields.Venues.CATEGORIES_ID);
			String imagePath = obj.getString(JsonGetFields.Venues.IMAGE_PATH);
			double rating = obj.getDouble(JsonGetFields.Venues.RATING);
			String phone = obj.getString(JsonGetFields.Venues.PHONE);
			String email = obj.getString(JsonGetFields.Venues.EMAIL);
			String website = obj.getString(JsonGetFields.Venues.WEBISTE);
			String catName = obj.getString(JsonGetFields.Venues.CATEGORY_NAME);
			
			return new Venue(venueID, userID, name, location, categoryID, imagePath, rating, phone,
					email, website, catName);
		} 
		catch (JSONException e) 
		{
			Log.e("Venue Model", "JSON Parse error");
			e.printStackTrace();
		}
		
		return null;
	}
}
