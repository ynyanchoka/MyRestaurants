package com.moringaschool.myrestaurants.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.myrestaurants.Constants;
import com.moringaschool.myrestaurants.R;
import com.moringaschool.myrestaurants.fragments.RestaurantDetailFragment;
import com.moringaschool.myrestaurants.models.Business;
import com.moringaschool.myrestaurants.ui.RestaurantDetailActivity;
import com.moringaschool.myrestaurants.util.OnRestaurantSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//public class RestaurantListAdapter  extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {
//    private List<Business> mRestaurants;
//    private Context mContext;
//
//    public RestaurantListAdapter(Context context, List<Business> restaurants) {
//        mContext = context; //to create our ViewHolder
//        mRestaurants = restaurants;//mRestaurants to calculate the item count, which informs the RecyclerView how many individual list item Views it will need to recycle
//    }
//
//    //.onCreateViewHolder() method inflates the restaurant_list_item layout, and creates the ViewHolder object required from the adapter.
//
//    @Override
//    public RestaurantListAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);
//        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
//        return viewHolder;
//    }
////.onBindViewHolder() updates the contents of the ItemView to reflect the restaurant in the given position.
//    @Override
//    public void onBindViewHolder(RestaurantListAdapter.RestaurantViewHolder holder, int position) {
//        holder.bindRestaurant(mRestaurants.get(position));
//    }
//
//    // sets the number of items the adapter will display.
//
//    @Override
//    public int getItemCount() {
//        return mRestaurants.size();
//    }
//
//    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        @BindView(R.id.restaurantImageView)
//        ImageView mRestaurantImageView;
//        @BindView(R.id.restaurantNameTextView)
//        TextView mNameTextView;
//        @BindView(R.id.categoryTextView)
//        TextView mCategoryTextView;
//        @BindView(R.id.ratingTextView)
//        TextView mRatingTextView;
//
//        private Context mContext;
//
//        public RestaurantViewHolder(View itemView) {
//            super(itemView);
//
//            ButterKnife.bind(this, itemView);
//            mContext = itemView.getContext();
//            itemView.setOnClickListener(this);//implement the View.OnClickListenerinterface.
//        }
//
//        public void bindRestaurant(Business restaurant) {
//            mNameTextView.setText(restaurant.getName());
//            mCategoryTextView.setText(restaurant.getCategories().get(0).getTitle());
//            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
//            Picasso.get().load(restaurant.getImageUrl()).into(mRestaurantImageView);
//        }
//
//        @Override
//        public void onClick(View v) {
//            int itemPosition = getLayoutPosition();//retrieve the position of the specific list item clicked.
//            Intent intent = new Intent(mContext, RestaurantDetailActivity.class);//reates an intent to navigate to our RestaurantDetailActivity,
//            intent.putExtra("position", itemPosition);
//            intent.putExtra("restaurants", Parcels.wrap(mRestaurants));
//            mContext.startActivity(intent);
//        }
//    }
//}

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Business> mRestaurants = new ArrayList<>();
    private Context mContext;
    private OnRestaurantSelectedListener mOnRestaurantSelectedListener;


    public RestaurantListAdapter(Context context, ArrayList<Business> restaurants, OnRestaurantSelectedListener restaurantSelectedListener){
        mContext = context;
        mRestaurants = restaurants;
        mOnRestaurantSelectedListener = restaurantSelectedListener;

    }

    @Override
    public RestaurantListAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);
        RestaurantViewHolder viewHolder =  new RestaurantViewHolder(view, mRestaurants, mOnRestaurantSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantListAdapter.RestaurantViewHolder holder, int position){
        holder.bindRestaurant(mRestaurants.get(position));
    }

    @Override
    public int getItemCount(){
        return mRestaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.restaurantImageView) ImageView mRestaurantImageView;
        @BindView(R.id.restaurantNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;
        private int mOrientation;

        public RestaurantViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            // Determines the current orientation of the device:
            mOrientation = itemView.getResources().getConfiguration().orientation;

            // Checks if the recorded orientation matches Android's landscape configuration.
            // if so, we create a new DetailFragment to display in our special landscape layout:
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }

        // Takes position of restaurant in list as parameter:
        private void createDetailFragment(int position){
            // Creates new RestaurantDetailFragment with the given position:
            RestaurantDetailFragment detailFragment = RestaurantDetailFragment.newInstance(mRestaurants, position);
            // Gathers necessary components to replace the FrameLayout in the layout with the RestaurantDetailFragment:
            FragmentTransaction ft = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
            //  Replaces the FrameLayout with the RestaurantDetailFragment:
            ft.replace(R.id.restaurantDetailContainer, detailFragment);
            // Commits these changes:
            ft.commit();
        }

        public void bindRestaurant(Business restaurant) {
            mNameTextView.setText(restaurant.getName());
            mCategoryTextView.setText(restaurant.getCategories().get(0).getTitle());
            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
            Picasso.get().load(restaurant.getImageUrl()).into(mRestaurantImageView);
        }

        @Override
        public void onClick(View v){
            // Determines the position of the restaurant clicked:
            int itemPosition = getLayoutPosition();
            if(mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_RESTAURANTS, Parcels.wrap(mRestaurants));
                mContext.startActivity(intent);
            }
        }
    }
}