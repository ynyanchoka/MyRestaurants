package com.moringaschool.myrestaurants.util;

public interface ItemTouchHelperAdapter {
// will be called each time the user moves an item by dragging it across the touch screen
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);//is called when an item has been dismissed with a swipe motion
}

