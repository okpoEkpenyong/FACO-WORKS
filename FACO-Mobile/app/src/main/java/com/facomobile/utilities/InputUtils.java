package com.facomobile.utilities;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;

public class InputUtils {

    private ArrayList<View> mViews;

    private InputUtils(View... inputFields) {
        if (mViews == null) mViews = new ArrayList<>();
        mViews.addAll(Arrays.asList(inputFields));
    }

    public static InputUtils init(View... inputFields) {
        return new InputUtils(inputFields);
    }

    public void enableInput() {
        for (View view : mViews) {
            if (view instanceof ProgressBar) view.setVisibility(View.GONE);
            else view.setEnabled(true);
            if (view instanceof Button) view.setAlpha(1f);
            if (view instanceof ImageView) {
                view.setClickable(true);
                view.setFocusable(true);
            }
        }
    }

    public void disableInput() {
        for (View view : mViews) {
            if (view instanceof ProgressBar) view.setVisibility(View.VISIBLE);
            else view.setEnabled(false);
            if (view instanceof Button) view.setAlpha(0.8f);
            if (view instanceof ImageView) {
                view.setClickable(false);
                view.setFocusable(false);
            }
        }
    }

}
