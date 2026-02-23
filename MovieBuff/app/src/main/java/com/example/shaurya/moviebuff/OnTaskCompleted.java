package com.example.shaurya.moviebuff;

import android.widget.ProgressBar;

/**
 * Created by shaurya on 13/02/18.
 */

public interface OnTaskCompleted {
    void onTaskStarted();
    void onTaskCompleted(String data);
}
