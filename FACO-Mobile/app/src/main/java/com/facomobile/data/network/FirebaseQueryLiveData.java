//package com.facomobile.data.network;
//
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.LiveData;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//public class FirebaseQueryLiveData extends LiveData<DataSnapshot> {
//    public static final String ONE_TIME_QUERY = "one_time_query";
//    public static final String RECURRING_QUERY = "recurring_query";
//    private static final String LOG_TAG = FirebaseQueryLiveData.class.getSimpleName();
//    private final MyValueEventListener mListener = new MyValueEventListener();
//
//    private Query mQuery;
//    private String mQueryFrequency;
//
//    public FirebaseQueryLiveData(Query query, String queryFrequency) {
//        this.mQuery = query;
//        this.mQueryFrequency = queryFrequency;
//    }
//
//    public FirebaseQueryLiveData(DatabaseReference ref, String queryFrequency) {
//        this.mQuery = ref;
//        this.mQueryFrequency = queryFrequency;
//    }
//
//    public FirebaseQueryLiveData(String queryFrequency) {
//        this.mQueryFrequency = queryFrequency;
//        this.mQuery = null;
//    }
//
//    public FirebaseQueryLiveData() {
//        this.mQuery = null;
//        this.mQueryFrequency = "";
//    }
//
//    public void setQueryFrequency(String queryFrequency) {
//        this.mQueryFrequency = queryFrequency;
//    }
//
//    public void setQuery(Query query) {
//        this.mQuery = query;
//    }
//
//    public void setQuery(DatabaseReference ref) {
//        Log.d(LOG_TAG, "Setting the query...");
//        this.mQuery = ref;
//    }
//
//    @Override
//    protected void onActive() {
//        Log.d(LOG_TAG, "onActive");
//        switch (mQueryFrequency) {
//            case ONE_TIME_QUERY:
//                mQuery.addListenerForSingleValueEvent(mListener);
//                break;
//            case RECURRING_QUERY:
//                mQuery.addValueEventListener(mListener);
//                break;
//            default:
//                Log.d(LOG_TAG, "Incorrect query type passed");
//        }
//    }
//
//    @Override
//    protected void onInactive() {
//        Log.d(LOG_TAG, "onInactive");
//        if (mQueryFrequency.equals(RECURRING_QUERY))
//            mQuery.removeEventListener(mListener);
//    }
//
//    private class MyValueEventListener implements ValueEventListener {
//
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            Log.d(LOG_TAG, "Data has changed");
//            setValue(dataSnapshot);
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError databaseError) {
//            Log.e(LOG_TAG, "Can't listen to query " + mQuery, databaseError.toException());
//            setValue(null);
//        }
//    }
//
//}
