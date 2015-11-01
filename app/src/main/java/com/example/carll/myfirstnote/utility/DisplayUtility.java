package com.example.carll.myfirstnote.utility;

import android.app.Activity;
import android.util.DisplayMetrics;

public class DisplayUtility {

    private float density;
    private float dpWidth;
    private float dpHeight;

    public DisplayUtility(Activity activity) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        calculateDisplay(displayMetrics);
    }

    private void calculateDisplay(DisplayMetrics displayMetrics) {
        density = displayMetrics.density;
        dpWidth = displayMetrics.widthPixels / density;
        dpHeight = displayMetrics.heightPixels / density;
    }

    // getters
    public float getDensity() {
        return density;
    }

    public float getHeight() {
        return dpHeight;
    }

    public float getWidth() {
        return dpWidth;
    }
}
