package com.facomobile.doctors.data.network;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

import com.facomobile.doctors.data.AppRepository;
import com.facomobile.doctors.utilities.WorkUtils;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.iid.FirebaseInstanceId;
import com.stargradegroup.vtuservices.cta.utilities.InjectorUtils;

public class MessagingTokenUpdateWorker extends ListenableWorker {

    public static final String NEW_TOKEN = "new_token";
    private final String LOG_TAG = MessagingTokenUpdateWorker.class.getSimpleName();

    public MessagingTokenUpdateWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public ListenableFuture<Result> startWork() {
        ResolvableFuture<Result> result = ResolvableFuture.create();
        String token = getInputData().getString(NEW_TOKEN);
        AppRepository repository = InjectorUtils.INSTANCE.provideRepository(getApplicationContext());
        if (TextUtils.isEmpty(token)) {
            FirebaseInstanceId
                    .getInstance()
                    .getInstanceId()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful() || task.getResult() == null)
                            result.set(Result.retry());
                        else {
                            WorkUtils.scheduleMessagingTokenUpdateWork(getApplicationContext(), task.getResult().getToken());
                            result.set(Result.success());
                        }
                    });
        }
        String uid = repository.getUid();
        if (TextUtils.isEmpty(uid)) result.set(Result.retry());
        /*DatabaseReference tokenRef = FirebaseUtils.getCloudMessagingTokenRef(uid);
        tokenRef.setValue(token).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) result.set(Result.retry());
            else result.set(Result.success());
        });*/
        return result;
    }
}