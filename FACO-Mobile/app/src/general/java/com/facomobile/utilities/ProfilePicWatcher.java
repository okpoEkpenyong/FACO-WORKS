package com.facomobile.utilities;

import android.content.Context;
import android.os.FileObserver;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import java.io.File;

public class ProfilePicWatcher implements LifecycleObserver {

    private final FileObserver profilePicObserver;
    private final Lifecycle lifecycle;
    private boolean enabled = false;

    public ProfilePicWatcher(Context context, Lifecycle lifecycle, String uid, MutableLiveData<String> profilePicLiveData) {
        this.lifecycle = lifecycle;
        File filesDir = context.getFilesDir();
        profilePicObserver = new FileObserver(filesDir.getPath(), FileObserver.ALL_EVENTS) {
            @Override
            public void onEvent(int event, @Nullable String path) {
                String finalPath = path == null ? null :
                        path.substring(0, path.length() - 4);
                switch (event) {
                    case 256:
                    case 2:
                        // created or modified
                        if (uid == null) profilePicLiveData.postValue(finalPath);
                        else if ((uid).equals(path))
                            profilePicLiveData.postValue(uid);
                        break;
                    case 512:
                        // deleted
                        profilePicLiveData.postValue(finalPath);
                        break;
                }
            }
        };
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        if (enabled) profilePicObserver.startWatching();
    }

    public void enable() {
        enabled = true;
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED))
            profilePicObserver.startWatching();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        profilePicObserver.stopWatching();
    }

}
