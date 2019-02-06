package com.dating.app.idateu.Homepage.Pop_up;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class detectUserSwipe extends VerticalPager

    {
        public detectUserSwipe(Context context, AttributeSet attrs) {
            super(context, attrs);
            mGestureDetector = new GestureDetector(context, new XScrollDetector());
        }

        private GestureDetector mGestureDetector;
        private boolean mIsLockOnHorizontalAxis = false;

        // -----------------------------------------------------------------------
        //
        // Methods
        //
        // -----------------------------------------------------------------------
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // decide if horizontal axis is locked already or we need to check the scrolling direction
            if (!mIsLockOnHorizontalAxis)
                mIsLockOnHorizontalAxis = mGestureDetector.onTouchEvent(event);

            // release the lock when finger is up
            if (event.getAction() == MotionEvent.ACTION_UP)
                mIsLockOnHorizontalAxis = false;

            getParent().requestDisallowInterceptTouchEvent(mIsLockOnHorizontalAxis);
            return super.onTouchEvent(event);
        }

        // -----------------------------------------------------------------------
        //
        // Inner Classes
        //
        // -----------------------------------------------------------------------
        private class XScrollDetector extends GestureDetector.SimpleOnGestureListener {

            // -----------------------------------------------------------------------
            //
            // Methods
            //
            // -----------------------------------------------------------------------
            /**
             * @return true - if we're scrolling in X direction, false - in Y direction.
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return Math.abs(distanceX) > Math.abs(distanceY);
            }

        }

    }
