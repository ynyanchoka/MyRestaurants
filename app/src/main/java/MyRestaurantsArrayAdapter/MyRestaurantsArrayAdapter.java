package MyRestaurantsArrayAdapter;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyRestaurantsArrayAdapter extends ArrayAdapter {
    private Context mContext;//to get information regarding our app, or other parts of our app.required for many methods to run,
    private String[] mRestaurants;
    private String[] mCuisines;
//adapters need three pieces of information
//
//Information about where they are being invoked (Context),
//Information about which layout file they are utilizing (resource, in this case simple_list_item_1)
//Some form of data storage (an Array or ArrayList of String's or Objects, most likely).
    public MyRestaurantsArrayAdapter(Context mContext, int resource, String[] mRestaurants, String[] mCuisines) {
        super(mContext, resource); //allows you to call both the original and the overridden method from a subclass
        this.mContext = mContext;
        this.mRestaurants = mRestaurants;
        this.mCuisines = mCuisines;
    }

    @Override //Override some of ArrayAdapter’s methods and replace them with our own custom versions
    public Object getItem(int position) {
        String restaurant = mRestaurants[position];
        String cuisine = mCuisines[position];
        return String.format("%s \nServes great: %s", restaurant, cuisine);//\n is how we can create a new line
    }

    @Override
    public int getCount() {
        return mRestaurants.length;
    }
}
