package com.tooliveapp.globals;

public class JsonGetFields 
{
	public static final String ERROR = "error";
	
	public class Category
	{
		public static final String CATEGORIES_ARRAY = "categories";
		public static final String NAME = "name";
	}
	
	public class Venues
	{
		public static final String VENUES_ARRAY = "venues";
		public static final String VENUE_ID = "venue_id";
		public static final String USER_ID = "user_id";
		public static final String NAME = "name";
		public static final String LOCATION = "location";
		public static final String CATEGORIES_ID = "categories_id";
		public static final String IMAGE_PATH = "image_path";
		public static final String RATING = "rating";
		public static final String PHONE = "phone";
		public static final String EMAIL = "email";
		public static final String WEBISTE = "website";
		public static final String CATEGORY_NAME = "category_name";
	}
	
	public class Feed
	{
		public static final String FEED_ARRAY = "feeds";
		public static final String VENUE_NAME = "name";
		public static final String VENUE_IMAGE_PATH = "image_path";
		public static final String VENUE_ID = "venue_id";
		public static final String FEED_ID = "feed_id";
		public static final String COMMENT = "comment";
		public static final String CREATED_AT = "created_at";
	}
}
