package com.moringaschool.myrestaurants.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.myrestaurants.fragments.RestaurantDetailFragment;
import com.moringaschool.myrestaurants.models.Business;

import java.util.ArrayList;
import java.util.List;

public class RestaurantPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Business> mRestaurants;
    private String mSource;



    //constructor where we set the required FragmentManager and array list of restaurants we will be swiping through.
    public RestaurantPagerAdapter(@NonNull FragmentManager fm,int behavior, ArrayList<Business> restaurants,String source) {
        super(fm, behavior);
        mRestaurants = restaurants;
        mSource = source;

    }

    @Override
    // returns an instance of the RestaurantDetailFragment for the restaurant in the position provided as an argument.
    public Fragment getItem(int position) {
//        return RestaurantDetailFragment.newInstance(mRestaurants.get(position));
        return RestaurantDetailFragment.newInstance(mRestaurants, position, mSource);
    }

    @Override
    //determines how many restaurants are in our Array List. This lets our adapter know how many fragments it must create
    public int getCount() {
        return mRestaurants.size();
    }
    @Override
    //updates the title that appears in the scrolling tabs at the top of the screen.
    public CharSequence getPageTitle(int position) {
        return mRestaurants.get(position).getName();
    }
}
