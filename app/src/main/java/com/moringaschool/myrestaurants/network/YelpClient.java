package com.moringaschool.myrestaurants.network;

import static com.moringaschool.myrestaurants.Constants.YELP_API_KEY;
import static com.moringaschool.myrestaurants.Constants.YELP_BASE_URL;

import com.moringaschool.myrestaurants.Constants;
import com.moringaschool.myrestaurants.models.Business;
import com.moringaschool.myrestaurants.models.Category;
import com.moringaschool.myrestaurants.models.Coordinates;
import com.moringaschool.myrestaurants.models.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YelpClient {
    private static Retrofit retrofit = null;

    public static YelpApi getClient() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder() //intercepts each request and adds an HTTP Authorization header with the value of the Yelp API token
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("Authorization", YELP_API_KEY)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();
// converter from JSON to java objects
            retrofit = new Retrofit.Builder()
                    .baseUrl(YELP_BASE_URL) //appended to the endpoints we defined in the interface class.
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()) //handles data serialization from JSON to Java objects
                    .build();
        }

        return retrofit.create(YelpApi.class);
    }

}
