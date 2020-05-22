package com.facomobile.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class CustomDeviceUtils {
    @SuppressLint("MissingPermission")
    public static String getDeviceID(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) return "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) return "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return CustomStringUtiils.getNonNullString(telephonyManager.getDeviceId());
        else {
            String imei = telephonyManager.getImei();
            if (TextUtils.isEmpty(imei))
                return CustomStringUtiils.getNonNullString(telephonyManager.getMeid());
            return imei;
        }
    }
}
