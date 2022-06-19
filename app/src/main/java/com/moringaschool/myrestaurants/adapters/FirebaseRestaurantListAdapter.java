package com.moringaschool.myrestaurants.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.moringaschool.myrestaurants.Constants;
import com.moringaschool.myrestaurants.R;
import com.moringaschool.myrestaurants.fragments.RestaurantDetailFragment;
import com.moringaschool.myrestaurants.models.Business;
import com.moringaschool.myrestaurants.ui.RestaurantDetailActivity;
import com.moringaschool.myrestaurants.util.ItemTouchHelperAdapter;
import com.moringaschool.myrestaurants.util.OnStartDragListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

// FirebaseRecyclerAdapter requires the class of the data that will populate the RecyclerView and the database reference or query.

//public class FirebaseRestaurantListAdapter extends FirebaseRecyclerAdapter<Business, FirebaseRestaurantViewHolder> implements ItemTouchHelperAdapter {
////    private Query mRef;
////    private OnStartDragListener mOnStartDragListener;
////    private Context mContext;
////    private ChildEventListener mChildEventListener;
////    private ArrayList<Business> mRestaurants = new ArrayList<>();
//
//    private DatabaseReference mRef;
//    private OnStartDragListener mOnStartDragListener;
//    private Context mContext;
//
//    public FirebaseRestaurantListAdapter(FirebaseRecyclerOptions<Business> options,
////                                         Query ref,
//                                         DatabaseReference ref,
//                                         OnStartDragListener onStartDragListener,
//                                         Context context){
//        super(options);
//        mRef = ref.getRef();
//        mOnStartDragListener = onStartDragListener;
//        mContext = context;

//        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                mRestaurants.add(dataSnapshot.getValue(Business.class));
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

//    @Override
//    protected void onBindViewHolder(@NonNull final FirebaseRestaurantViewHolder firebaseRestaurantViewHolder, int position, @NonNull Business restaurant) {
//        firebaseRestaurantViewHolder.bindRestaurant(restaurant);
//        firebaseRestaurantViewHolder.mRestaurantImageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
//                    mOnStartDragListener.onStartDrag(firebaseRestaurantViewHolder);
//                }
//                return false;
//            }
//        });
//
//        @Override
//        protected void onBindViewHolder(@NonNull FirebaseRestaurantViewHolder firebaseRestaurantViewHolder, int position, @NonNull Business restaurant) {
//            firebaseRestaurantViewHolder.bindRestaurant(restaurant);
//        }

//        firebaseRestaurantViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
//                intent.putExtra("position", firebaseRestaurantViewHolder.getAdapterPosition());
//                intent.putExtra("restaurants", Parcels.wrap(mRestaurants));
//                Log.d("Something",mRestaurants.get(0).getName());
//                mContext.startActivity(intent);
//
//            }
//        });
//    }
//
//    @NonNull
//    @Override
//    public FirebaseRestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item_drag, parent, false);
//        return new FirebaseRestaurantViewHolder(view);
//    }
//
//    @Override
//    public boolean onItemMove(int fromPosition, int toPosition){
//        return false;
//    }
//
//    @Override
//    public void onItemDismiss(int position){
//
//    }



//    @Override
//    public boolean onItemMove(int fromPosition, int toPosition){
//        Collections.swap(mRestaurants, fromPosition, toPosition);
//        notifyItemMoved(fromPosition, toPosition);
//        setIndexInFirebase();
//        return false;
//    }
//
//    @Override
//    public void onItemDismiss(int position){
//        mRestaurants.remove(position);
//        getRef(position).removeValue();
//    }
//
//    private void setIndexInFirebase(){
//        for(Business restaurant: mRestaurants){
//            int index = mRestaurants.indexOf(restaurant);
//            DatabaseReference mReference = getRef(index);
//            restaurant.setIndex(Integer.toString(index));
//            mReference.setValue(restaurant);
//        }
//    }
//
//    @Override
//    public void stopListening(){
//        super.stopListening();
//        mRef.removeEventListener(mChildEventListener);
////    }
//}
public class FirebaseRestaurantListAdapter extends FirebaseRecyclerAdapter<Business, FirebaseRestaurantViewHolder> implements ItemTouchHelperAdapter {
    private Query mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Business> mRestaurants = new ArrayList<>();
    private int mOrientation;

    public FirebaseRestaurantListAdapter(FirebaseRecyclerOptions<Business> options,
                                         Query ref,
                                         OnStartDragListener onStartDragListener,
                                         Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mRestaurants.add(dataSnapshot.getValue(Business.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onBindViewHolder(@NonNull final FirebaseRestaurantViewHolder firebaseRestaurantViewHolder, int position, @NonNull Business restaurant) {
        firebaseRestaurantViewHolder.bindRestaurant(restaurant);

        mOrientation = firebaseRestaurantViewHolder.itemView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
            createDetailFragment(0);
        }

        firebaseRestaurantViewHolder.mRestaurantImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(firebaseRestaurantViewHolder);
                }
                return false;
            }
        });

        firebaseRestaurantViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = firebaseRestaurantViewHolder.getAdapterPosition();
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                    createDetailFragment(itemPosition);
                } else {
                    Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(Constants.EXTRA_KEY_RESTAURANTS, Parcels.wrap(mRestaurants));
                    intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_SAVED);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    private void createDetailFragment(int position){
        // Creates new RestaurantDetailFragment with the given position:
        RestaurantDetailFragment detailFragment = RestaurantDetailFragment.newInstance(mRestaurants, position,Constants.SOURCE_SAVED);
        // Gathers necessary components to replace the FrameLayout in the layout with the RestaurantDetailFragment:
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        //  Replaces the FrameLayout with the RestaurantDetailFragment:
        ft.replace(R.id.restaurantDetailContainer, detailFragment);
        // Commits these changes:
        ft.commit();
    }

    @NonNull
    @Override
    public FirebaseRestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item_drag, parent, false);
        return new FirebaseRestaurantViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        Collections.swap(mRestaurants, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInFirebase();
        return false;
    }

    @Override
    public void onItemDismiss(int position){
        mRestaurants.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase(){
        for(Business restaurant: mRestaurants){
            int index = mRestaurants.indexOf(restaurant);
            DatabaseReference mReference = getRef(index);
//            restaurant.setIndex(Integer.toString(index));
//            mReference.setValue(restaurant);
            mReference.child("index").setValue(Integer.toString(index));

        }
    }

    @Override
    public void stopListening(){
        super.stopListening();
        mRef.removeEventListener(mChildEventListener);
    }
}