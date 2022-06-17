package com.moringaschool.myrestaurants.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.moringaschool.myrestaurants.R;
import com.moringaschool.myrestaurants.adapters.RestaurantPagerAdapter;
import com.moringaschool.myrestaurants.models.Business;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RestaurantDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private RestaurantPagerAdapter adapterViewPager;
    ArrayList<Business> mRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        ButterKnife.bind(this);
//pull out our ArrayList<Restaurant> Parcelable using the unwrap() method on our "restaurants" intent extra
        mRestaurants = Parcels.unwrap(getIntent().getParcelableExtra("restaurants"));
        int startingPosition = getIntent().getIntExtra("position", 0);
// create a new pager adapter called adapterViewPager, providing the mRestaurants as an argument.
        adapterViewPager = new RestaurantPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mRestaurants);
        mViewPager.setAdapter(adapterViewPager);//instruct our ViewPager to use this new adapter
        mViewPager.setCurrentItem(startingPosition);//set the current item to the position of the item that was just clicked on.
        Log.d("Hello",mRestaurants.get(0).getName());

    }
}