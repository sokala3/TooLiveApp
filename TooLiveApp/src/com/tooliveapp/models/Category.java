package com.tooliveapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Category 
{
	public Category(String name, int categoryID) {
		super();
		this.name = name;
		this.categoryID = categoryID;
	}
	
	private String name;
	private int categoryID;
	
	public String getName() 
	{
		return name;
	}
	public int getCategoryID() 
	{
		return categoryID;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public static Category decodeJSON(JSONObject obj)
	{
		try {		
			int categoryID = obj.getInt("categories_id");
			String name = obj.getString("name");
			
			return new Category(name, categoryID);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
