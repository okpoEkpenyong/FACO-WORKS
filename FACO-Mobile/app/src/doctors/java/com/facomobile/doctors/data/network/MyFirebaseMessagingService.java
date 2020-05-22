package com.facomobile.doctors.data.network;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.facomobile.doctors.data.database.AppDatabase;
import com.facomobile.doctors.utilities.WorkUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String LOG_TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(LOG_TAG, "Refreshed token: " + token);
        WorkUtils.scheduleMessagingTokenUpdateWork(this, token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.d(LOG_TAG, "Message data payload: " + remoteMessage.getData());

            Map<String, String> data = remoteMessage.getData();
            String messageType = "";// data.get(AppConstants.COLUMN_TYPE);
            if (messageType == null || TextUtils.isEmpty(messageType)) return;
            AppDatabase database = AppDatabase.getInstance(this);
            switch (messageType) {
                case "":
                    //
                    break;
                default:
                    Crashlytics.logException(new IllegalArgumentException("Invalid message type"));
            }
        }
    }
}