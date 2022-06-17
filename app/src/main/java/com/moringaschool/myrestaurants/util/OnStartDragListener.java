package com.moringaschool.myrestaurants.util;

import androidx.recyclerview.widget.RecyclerView;
//called when the user begins a 'drag-and-drop' interaction with the touchscreen.
public interface OnStartDragListener {
    void onStartDrag(RecyclerView.ViewHolder viewHolder);//viewHolder represents the RecyclerView holder corresponding to the object being dragged.
}
