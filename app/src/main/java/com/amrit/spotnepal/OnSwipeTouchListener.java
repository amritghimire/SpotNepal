package com.amrit.spotnepal;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {
    }

    public void onTap(){

    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
           // if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight();
                else if(distanceX<0)
                    onSwipeLeft();
                else
                    onTap();
                return true;
           // }
           // return false;
        }
    }

}
/*
Usage:

        imageView.setOnTouchListener(new OnSwipeTouchListener(MyActivity.this) {
public void onSwipeTop() {
        Toast.makeText(MyActivity.this, "top", Toast.LENGTH_SHORT).show();
        }
public void onSwipeRight() {
        Toast.makeText(MyActivity.this, "right", Toast.LENGTH_SHORT).show();
        }
public void onSwipeLeft() {
        Toast.makeText(MyActivity.this, "left", Toast.LENGTH_SHORT).show();
        }
public void onSwipeBottom() {
        Toast.makeText(MyActivity.this, "bottom", Toast.LENGTH_SHORT).show();
        }

        });
        */