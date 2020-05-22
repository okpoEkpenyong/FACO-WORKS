/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.facomobile.doctors.data;


import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.facomobile.AppExecutors;
import com.facomobile.doctors.data.database.DoctorDao;
import com.facomobile.doctors.data.network.FirebaseNetworkOperations;
import com.facomobile.doctors.ui.SplashActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;

@SuppressWarnings("unchecked")
public class AppRepository {
    private static final String LOG_TAG = AppRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static AppRepository sInstance;
    private final PatientDao mPatientDao;
    private final FirebaseNetworkOperations mFirebaseNetworkOperations;
    private final AppExecutors mExecutors;

    private AppRepository(PatientDao patientDao, FirebaseNetworkOperations firebaseNetworkOperations,
                          AppExecutors executors) {
        mPatientDao = patientDao;
        mFirebaseNetworkOperations = firebaseNetworkOperations;
        mExecutors = executors;
    }

    public synchronized static AppRepository getInstance(PatientDao patientDao,
                                                         FirebaseNetworkOperations firebaseNetworkOperations,
                                                         AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppRepository(patientDao, firebaseNetworkOperations, executors);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }


    public LiveData<Task<UploadTask.TaskSnapshot>> uploadFile(Uri uri, String uid) {
        return mFirebaseNetworkOperations.uploadFile(uri, uid);
    }

    public String getUid() {
        return mFirebaseNetworkOperations.getUid();
    }

    public String getCurrentUserPhoneNumber() {
        return mFirebaseNetworkOperations.getCurrentUserPhoneNumber();
    }

    public void fetchFile(File path, String uid) {
        mFirebaseNetworkOperations.fetchFile(path, uid);
    }

    public LiveData<Task<FileDownloadTask.TaskSnapshot>> fetchFileWithResult(File path, @NonNull String visitorId) {
        return mFirebaseNetworkOperations.fetchFileWithResult(path, visitorId);
    }

    public SplashActivity.AUTH_STATUS getUserAuthStatus() {
        return mFirebaseNetworkOperations.getUserAuthStatus();
    }

    // END OF USER OPERATIONS
}