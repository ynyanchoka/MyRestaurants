package com.moringaschool.myrestaurants.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.moringaschool.myrestaurants.BuildConfig;
import com.moringaschool.myrestaurants.Constants;
import com.moringaschool.myrestaurants.R;
import com.moringaschool.myrestaurants.adapters.RestaurantListAdapter;
import com.moringaschool.myrestaurants.models.Business;
import com.moringaschool.myrestaurants.models.YelpBusinessesSearchResponse;
import com.moringaschool.myrestaurants.network.YelpApi;
import com.moringaschool.myrestaurants.network.YelpClient;
import com.moringaschool.myrestaurants.ui.RestaurantListActivity;
import com.moringaschool.myrestaurants.util.OnRestaurantSelectedListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class RestaurantListFragment extends Fragment {
        @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
//        @BindView(R.id.progressBar) ProgressBar mProgressBar;
//        @BindView(R.id.errorTextView) TextView mErrorTextView;
        private RestaurantListAdapter mAdapter;
        private ArrayList<Business> restaurants = new ArrayList<>();



        private SharedPreferences mSharedPreferences;
        private SharedPreferences.Editor mEditor;
        private String mRecentAddress;
        private OnRestaurantSelectedListener mOnRestaurantSelectedListener;

        @Override
        public void onAttach(Context context){
                super.onAttach(context);
                try{
                        mOnRestaurantSelectedListener = (OnRestaurantSelectedListener) context;
                } catch (ClassCastException e){
                        throw new ClassCastException(context.toString() + e.getMessage());
                }
        }


        public RestaurantListFragment() {
                // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle saveInstance){
                super.onCreate(saveInstance);
                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                mEditor = mSharedPreferences.edit();
                // Instructs fragment to include menu options:
                setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
                ButterKnife.bind(this, view);
                mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
                if (mRecentAddress != null) {
                        getRestaurants(mRecentAddress);
                }
                // Inflate the layout for this fragment
                return view;
        }

        public void getRestaurants(String location) {

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getRestaurants(location, "restaurants");

        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
                @Override
                public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {


                        if (response.isSuccessful()) {
                                restaurants = response.body().getBusinesses();
                                mAdapter = new RestaurantListAdapter(getActivity(), restaurants, mOnRestaurantSelectedListener);
                                mRecyclerView.setAdapter(mAdapter);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                mRecyclerView.setLayoutManager(layoutManager);
                                mRecyclerView.setHasFixedSize(true);

                                showRestaurants();
                        } else {
//                                showUnsuccessfulMessage();
                        }
                }

                @Override
                public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
//                        hideProgressBar();
//                        showFailureMessage();
                }

        });
}


        @Override
        // Method is now void, menu inflater is now passed in as argument:
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
                // Call super to inherit method from parent:
                super.onCreateOptionsMenu(menu, inflater);
                inflater.inflate(R.menu.menu_search, menu);
                MenuItem menuItem = menu.findItem(R.id.action_search);
                SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String s) {
                                addToSharedPreferences(s);
                                getRestaurants(s);
                                return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String s) {
                                return false;
                        }
                });
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item){
                return super.onOptionsItemSelected(item);
        }

        private void addToSharedPreferences(String location) {
                mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
        }

        private void showRestaurants() {
                mRecyclerView.setVisibility(View.VISIBLE);
        }

//        private void hideProgressBar() {
//                mProgressBar.setVisibility(View.GONE);
//        }
//
//        private void showUnsuccessfulMessage() {
//                mErrorTextView.setText("Something went wrong. Please try again later");
//                mErrorTextView.setVisibility(View.VISIBLE);
//        }
//        private void showFailureMessage() {
//                mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
//                mErrorTextView.setVisibility(View.VISIBLE);
//        }

}