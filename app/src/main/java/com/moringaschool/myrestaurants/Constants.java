package com.moringaschool.myrestaurants;

import com.moringaschool.myrestaurants.BuildConfig;

public class Constants {
    public static final String YELP_BASE_URL = "https://api.yelp.com/v3/";
    public static final String YELP_API_KEY = BuildConfig.YELP_API_KEY;
    //Data persistence _ Shared Preferences
    public static final String PREFERENCES_LOCATION_KEY = "location";

    //Child node name for saving the values for searched Locations in Firebase
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";

    //Restaurant node key
    public static final String FIREBASE_CHILD_RESTAURANTS = "restaurants";

    //"index" key of our Restaurant objects used for referencing objects during querying
    public static final String FIREBASE_QUERY_INDEX = "index";

    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_RESTAURANTS = "restaurants";
    public static final String YELP_LOCATION_QUERY_PARAMETER = "location";

}
