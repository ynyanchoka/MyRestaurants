package com.moringaschool.myrestaurants.network;

import com.moringaschool.myrestaurants.models.YelpBusinessesSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {
    @GET("businesses/search")
    Call<YelpBusinessesSearchResponse> getRestaurants(
            @Query("location") String location, //annotation accepts a string which should be the name of the query
            @Query("term") String term
    );
}
