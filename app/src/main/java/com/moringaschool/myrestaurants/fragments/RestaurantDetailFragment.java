package com.moringaschool.myrestaurants.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.myrestaurants.Constants;
import com.moringaschool.myrestaurants.R;
import com.moringaschool.myrestaurants.models.Business;
import com.moringaschool.myrestaurants.models.Category;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.restaurantImageView)
    ImageView mImageLabel;
    @BindView(R.id.restaurantNameTextView) TextView mNameLabel;
    @BindView(R.id.cuisineTextView)
    TextView mCategoriesLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;


    private Business mRestaurant;
    private ArrayList<Business> mRestaurants;
    private int mPosition;

    public RestaurantDetailFragment() {
        // Required empty public constructor
    }


//    public static RestaurantDetailFragment newInstance(Business restaurant) {//used instead of a constructor and returns a new instance of our RestaurantDetailFragment.
public static RestaurantDetailFragment newInstance(ArrayList<Business> restaurants, Integer position){
    RestaurantDetailFragment restaurantDetailFragment = new RestaurantDetailFragment();
    Bundle args = new Bundle();

    args.putParcelable(Constants.EXTRA_KEY_RESTAURANTS, Parcels.wrap(restaurants));
    args.putInt(Constants.EXTRA_KEY_POSITION, position);

    restaurantDetailFragment.setArguments(args);
    return restaurantDetailFragment;
}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mRestaurants = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_RESTAURANTS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mRestaurant = mRestaurants.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get()
                .load(mRestaurant.getImageUrl())
                .into(mImageLabel);
        mNameLabel.setText(mRestaurant.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getCategories()));
        mRatingLabel.setText(Double.toString(mRestaurant.getRating()) + "/5");
        mPhoneLabel.setText(mRestaurant.getPhone());
        mAddressLabel.setText(mRestaurant.getLocation().toString());

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);
        mSaveRestaurantButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v){
        if (v == mWebsiteLabel){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mRestaurant.getUrl()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mRestaurant.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + mRestaurant.getCoordinates().getLatitude()
                    + "," + mRestaurant.getCoordinates().getLongitude()
                    + "?q=(" + mRestaurant.getName() + ")"));
            startActivity(mapIntent);
        }
        if (v == mSaveRestaurantButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
                    .child(uid);
            DatabaseReference pushRef = restaurantRef.push();
            String pushId = pushRef.getKey();
            mRestaurant.setPushId(pushId);
            pushRef.setValue(mRestaurant);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}