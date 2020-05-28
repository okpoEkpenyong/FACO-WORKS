package com.facomobile.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.facomobile.R;

public class CustomFilePicker extends LinearLayout {

    private Uri mUri;

    private TextView mTvPicker;
    private Button mBtnPreview;

    public CustomFilePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            View mFilePicker = inflater.inflate(R.layout.custom_file_picker_view, this, true);
            mTvPicker = mFilePicker.findViewById(R.id.tv_file_picker);
            mBtnPreview = mFilePicker.findViewById(R.id.btn_file_picker_preview);
        }
    }

    public CustomFilePicker(Context context) {
        this(context, null);
    }

    public void setPickAction(Runnable callback) {
        mTvPicker.setOnClickListener(view -> callback.run());
    }

    public void setPreviewAction(Runnable callback) {
        mBtnPreview.setOnClickListener(view -> callback.run());
    }

    public Uri getDegreePhotoUri() {
        return mUri;
    }

    public void setDegreePhotoUri(Uri uri) {
        mUri = uri;
        mBtnPreview.setEnabled(mUri != null);
    }

    public boolean isPhotoPicked() {
        return mUri != null;
    }
}
