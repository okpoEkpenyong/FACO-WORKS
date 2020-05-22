package com.facomobile.utilities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class CustomStringUtiils {
    public static boolean copyToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        if (clipboard == null) return false;
        else {
            clipboard.setPrimaryClip(clip);
            return true;
        }
    }

    public static String getNonNullString(String source) {
        return source == null ? "" : source;
    }

    @SuppressWarnings("CharsetObjectCanBeUsed")
    public static String encodeToBase64(String stringToEncode) {
        byte[] stringBytes = new byte[0];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            stringBytes = stringToEncode.getBytes(StandardCharsets.UTF_8);
        } else {
            try {
                stringBytes = stringToEncode.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                return stringToEncode;
            }
        }
        return Base64.encodeToString(stringBytes, Base64.NO_WRAP);
    }

    @SuppressWarnings("CharsetObjectCanBeUsed")
    public static String decodeFromBase64(String stringToDecode) {
        byte[] data = Base64.decode(stringToDecode, Base64.DEFAULT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return new String(data, StandardCharsets.UTF_8);
        } else {
            try {
                return new String(data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
    }

    /*public static void togglePasswordVisibility(Context context,
                                                EditText etPassword, ImageView toggleView) {
        if (etPassword.getInputType() ==
                (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            toggleView.setImageDrawable(ContextCompat.getDrawable(context,
                    R.drawable.ic_visibility_off_black_24dp));
            ImageViewCompat.setImageTintList(toggleView,
                    ColorStateList.valueOf(ContextCompat.getColor(context,
                            android.R.color.darker_gray)));
        } else if (etPassword.getInputType() ==
                (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
            toggleView.setImageDrawable(ContextCompat.getDrawable(context,
                    R.drawable.ic_visibility_black_24dp));
            ImageViewCompat.setImageTintList(toggleView,
                    ColorStateList.valueOf(ContextCompat.getColor(context,
                            android.R.color.darker_gray)));
        }
        etPassword.setSelection(etPassword.getText().toString().length());
    }*/
}
