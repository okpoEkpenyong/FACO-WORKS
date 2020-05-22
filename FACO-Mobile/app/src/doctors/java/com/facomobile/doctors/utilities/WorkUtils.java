package com.facomobile.doctors.utilities;

import android.content.Context;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.facomobile.doctors.data.network.MessagingTokenUpdateWorker;


public class WorkUtils {

    private static final String LOG_TAG = WorkUtils.class.getSimpleName();

    /*public static void scheduleSignOutWork(Context context) {
        Constraints updateConstraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest signOutWork = new OneTimeWorkRequest.Builder(SignOutWorker.class)
                .setConstraints(updateConstraints)
                .build();
        Log.d(LOG_TAG, "Signout work enqueued");
        WorkManager.getInstance(context).enqueue(signOutWork);
    }*/

    public static void cancelAuthRequiredWork(Context context) {
        WorkManager.getInstance(context).cancelAllWorkByTag("authRequiredWork");
    }

    public static void scheduleMessagingTokenUpdateWork(Context context) {
        scheduleMessagingTokenUpdateWork(context, null);
    }

    public static void scheduleMessagingTokenUpdateWork(Context context, String token) {
        Data data = new Data.Builder()
                .putString(MessagingTokenUpdateWorker.NEW_TOKEN, token)
                .build();
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MessagingTokenUpdateWorker.class)
                .setInputData(data)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(work);
    }


}
