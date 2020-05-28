package com.facomobile.doctors.data.network;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.crashlytics.android.Crashlytics;
import com.facomobile.doctors.ui.SplashActivity;
import com.facomobile.utilities.FirebaseUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

@SuppressWarnings("unused, unchecked")
public class FirebaseNetworkOperations {

    private static final String LOG_TAG = FirebaseNetworkOperations.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static FirebaseNetworkOperations sInstance;
    private FirebaseAuth mAuth;

    private FirebaseNetworkOperations() {
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null)
            Crashlytics.setUserIdentifier(mAuth.getCurrentUser().getUid());
    }

    public static FirebaseNetworkOperations getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new FirebaseNetworkOperations();
            }
        }
        return sInstance;
    }

    public String getUid() {
        return mAuth.getCurrentUser() == null ? null : mAuth.getCurrentUser().getUid();
    }

    public String getCurrentUserPhoneNumber() {
        return mAuth.getCurrentUser() == null ? null : mAuth.getCurrentUser().getPhoneNumber();
    }

    public String getUserDisplayName() {
        FirebaseUser user = mAuth.getCurrentUser();
        return user == null ? null : user.getDisplayName();
    }

    public void setUserDisplayName(String displayName) {
        if (displayName != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build();
                Log.d(LOG_TAG, "Starting user profile updates");
                user.updateProfile(profileUpdates).addOnCompleteListener(task ->
                        Log.d(LOG_TAG, task.isSuccessful() ? "User profile updates successful" : "User profile updates failed"));
            }
        }
    }

    public SplashActivity.AUTH_STATUS getUserAuthStatus() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser == null) return SplashActivity.AUTH_STATUS.SIGNED_OUT;
        return SplashActivity.AUTH_STATUS.SIGNED_IN;
//        if (firebaseUser.isEmailVerified())
//            return SplashActivity.AUTH_STATUS.SIGNED_IN_EMAIL_VERIFIED;
//        else return SplashActivity.AUTH_STATUS.SIGNED_IN_EMAIL_UNVERIFIED;
    }

    public void fetchFile(File path) {
        String uid = getUid();
        if (!TextUtils.isEmpty(uid))
            fetchFile(path, uid);
    }

    public void fetchFile(File path, @NonNull String uid) {
        StorageReference profilePicRef = FirebaseUtils.getUserStorageRef(uid);
        profilePicRef.getFile(path);
    }

    public LiveData<Task<FileDownloadTask.TaskSnapshot>> fetchFileWithResult(File path, @NonNull String userId) {
        MutableLiveData<Task<FileDownloadTask.TaskSnapshot>> liveData = new MutableLiveData<>();
        StorageReference profilePicRef = FirebaseUtils.getUserStorageRef(userId);
        profilePicRef.getFile(path).addOnCompleteListener(liveData::setValue);
        return liveData;
    }

//    private FeedbackEntry createFeedbackFromHashMap(HashMap<String, Object> feedbackHashMap) {
//        try {
//            String id = (String) feedbackHashMap.get(FAMSConstants.COLUMN_ID);
//            String message = (String) feedbackHashMap.get(FAMSConstants.COLUMN_MESSAGE);
//            String conversationId = (String) feedbackHashMap.get(FAMSConstants.COLUMN_CONVERSATION_ID);
//            String sender = (String) feedbackHashMap.get(FAMSConstants.COLUMN_SENDER);
//            String recipient = (String) feedbackHashMap.get(FAMSConstants.COLUMN_RECIPIENT);
//            long timestamp = (long) feedbackHashMap.get(FAMSConstants.COLUMN_TIMESTAMP);
//            return new FeedbackEntry(id == null ? "" : id, message, conversationId, sender, recipient, timestamp);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }

//    public LiveData<Task<Void>> subscribeToNotificationMessages() {
//        MutableLiveData<Task<Void>> liveData = new MutableLiveData<>();
//        FirebaseMessaging.getInstance()
//                .subscribeToTopic(FAMSConstants.COLUMN_NOTIFICATION)
//                .addOnCompleteListener(liveData::setValue);
//        return liveData;
//    }

    // END OF CLOUD MESSAGING

    /*private class NotificationEntryDeserializer implements Function<DataSnapshot, NotificationEntry> {
        @Override
        public NotificationEntry apply(DataSnapshot dataSnapshot) {
            return dataSnapshot.getValue(NotificationEntry.class);
        }
    }*/

    public LiveData<Task<UploadTask.TaskSnapshot>> uploadFile(Uri uri, String uid) {
        MutableLiveData<Task<UploadTask.TaskSnapshot>> liveData = new MutableLiveData<>();
        StorageReference profilePicRef = FirebaseUtils.getUserStorageRef(uid);
        profilePicRef.putFile(uri).addOnCompleteListener(liveData::setValue);
        return liveData;
    }

//    private class FirebaseHashMapDeserializer implements Function<DataSnapshot, HashMap<String, Object>> {
//        @Override
//        public HashMap<String, Object> apply(DataSnapshot dataSnapshot) {
//            if (dataSnapshot == null) return null;
//            try {
//                return (HashMap<String, Object>) dataSnapshot.getValue();
//            } catch (DatabaseException databaseException) {
//                Crashlytics.logException(databaseException);
//                return null;
//            }
//        }
//    }
//
//    private class GuardEntryDeserializer implements Function<DataSnapshot, GuardEntry> {
//        @Override
//        public GuardEntry apply(DataSnapshot dataSnapshot) {
//            if (dataSnapshot == null) return null;
//            try {
//                return dataSnapshot.getValue(GuardEntry.class);
//            } catch (DatabaseException databaseException) {
//                Crashlytics.logException(databaseException);
//                return null;
//            }
//        }
//    }
}
