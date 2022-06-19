package com.moringaschool.myrestaurants.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.myrestaurants.Constants;
import com.moringaschool.myrestaurants.R;
import com.moringaschool.myrestaurants.models.Business;
import com.moringaschool.myrestaurants.ui.RestaurantDetailActivity;
import com.moringaschool.myrestaurants.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

//public class FirebaseRestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ItemTouchHelperViewHolder {
//    private static final int MAX_WIDTH = 200;
//    private static final int MAX_HEIGHT = 200;
//    View mView;
//    Context mContext;
//    public ImageView mRestaurantImageView;
//
//    public FirebaseRestaurantViewHolder(View itemView){
//        super(itemView);
//        mView = itemView;
//        mContext = itemView.getContext();
////        itemView.setOnClickListener(this);
//    }
//
//    public void bindRestaurant(Business restaurant){
//        mRestaurantImageView = mView.findViewById(R.id.restaurantImageView);
//        TextView nameTextView = mView.findViewById(R.id.restaurantNameTextView);
//        TextView categoryTextView = mView.findViewById(R.id.categoryTextView);
//        TextView ratingTextView = mView.findViewById(R.id.ratingTextView);
//
//        nameTextView.setText(restaurant.getName());
//        categoryTextView.setText(restaurant.getCategories().get(0).getTitle());
//        ratingTextView.setText("Rating: " + restaurant.getRating() + "/5");
//        Picasso.get().load(restaurant.getImageUrl()).into(mRestaurantImageView);
//
//        if (!restaurant.getImageUrl().contains("http")) {
//            try {
//                Bitmap imageBitmap = decodeFromFirebaseBase64(restaurant.getImageUrl());
//                mRestaurantImageView.setImageBitmap(imageBitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            // This block of code should already exist, we're just moving it to the 'else' statement:
//            Picasso.get().load(restaurant.getImageUrl()).into(mRestaurantImageView);
//            nameTextView.setText(restaurant.getName());
//            categoryTextView.setText(restaurant.getCategories().get(0).getTitle());
//            ratingTextView.setText("Rating: " + restaurant.getRating() + "/5");
//        }
//        nameTextView.setText(restaurant.getName());
//        categoryTextView.setText(restaurant.getCategories().get(0).getTitle());
//        ratingTextView.setText("Rating: " + restaurant.getRating() + "/5");
//    }
//
//    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
//        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
//        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
//    }
//
//    @Override
//    public void onClick(View view) {
//        final ArrayList<Business> restaurants = new ArrayList<>();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESTAURANTS).child(uid);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    restaurants.add(snapshot.getValue(Business.class));
//                }
//
//                int itemPosition = getLayoutPosition();
//                Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("restaurants", Parcels.wrap(restaurants));
//
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    @Override
//    public void onItemSelected() {
//        Log.d("Animation", "onItemSelected");
//        // we will add animations here
//
//        itemView.animate()
//                .alpha(0.7f)//alters the alpha level of an object (its transparency and/or opaqueness, essentially).
//                .scaleX(0.9f)
//                .scaleY(0.9f)
//                .setDuration(500);
//    }
//
//    @Override
//    public void onItemClear() {
//        Log.d("Animation", "onItemClear");
//        // we will add animations here
//        itemView.animate()
//                .alpha(1f)
//                .scaleX(1f)
//                .scaleY(1f);
//    }
//}

public class FirebaseRestaurantViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    View mView;
    Context mContext;
    public ImageView mRestaurantImageView;

    public FirebaseRestaurantViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindRestaurant(Business restaurant){
        mRestaurantImageView = mView.findViewById(R.id.restaurantImageView);
        TextView nameTextView = mView.findViewById(R.id.restaurantNameTextView);
        TextView categoryTextView = mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = mView.findViewById(R.id.ratingTextView);

        if (!restaurant.getImageUrl().contains("http")){
            try {
                Bitmap imageBitmap = decodeFromFirebaseBase64(restaurant.getImageUrl());
                mRestaurantImageView.setImageBitmap(imageBitmap);
            } catch (IOException e){
                e.printStackTrace();
            }
        }else {
            Picasso.get().load(restaurant.getImageUrl()).into(mRestaurantImageView);
//            nameTextView.setText(restaurant.getName());
//            categoryTextView.setText(restaurant.getCategories().get(0));
//            ratingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        }
        nameTextView.setText(restaurant.getName());
        categoryTextView.setText(restaurant.getCategories().get(0).getTitle());
        ratingTextView.setText("Rating: " + restaurant.getRating() + "/5");
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray,0, decodedByteArray.length);
    }

    @Override
    public void onItemSelected(){
        //Log.d("Animation", "onItemSelected");
        // we will add animations here
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear(){
        //Log.d("Animation", "onItemClear");
        // we will add animations here
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }
}
