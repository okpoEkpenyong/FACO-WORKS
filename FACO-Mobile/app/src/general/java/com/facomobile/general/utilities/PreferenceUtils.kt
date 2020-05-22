package com.facomobile.general.utilities

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.facomobile.R

object PreferenceUtils {

    private val LOG_TAG = PreferenceUtils::class.java.simpleName

    /*fun setInstitutionCode(context: Context, institutionCode: String) {
        val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(context.getString(R.string.pref_key_institution_code), institutionCode)
        editor.apply()
    }

    fun getInstitutionCode(context: Context): String? {
        val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString(context.getString(R.string.pref_key_institution_code), null)
    }*/

    /*public static void setPreferencesAuthStatus(Context context, boolean authStatus) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_key_auth_status), authStatus);
        editor.apply();
    }

    public static boolean getPreferencesAuthStatus(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(context.getString(R.string.pref_key_auth_status), false);
    }*/

    fun getOnboardingStatus(context: Context): Boolean {
        val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getBoolean(context.getString(R.string.pref_key_onboarding_status), false)
    }

    fun setOnboardingStatus(context: Context, onboardingStatus: Boolean) {
        val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(context.getString(R.string.pref_key_onboarding_status), onboardingStatus)
        editor.apply()
    }

    /*fun getPrefAuthStatus(context: Context): Boolean {
        val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getBoolean(context.getString(R.string.pref_key_auth_status), false)
    }

    fun setPrefAuthStatus(context: Context, authStatus: Boolean) {
        val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(context.getString(R.string.pref_key_auth_status), authStatus)
        editor.apply()
    }*/

    /*public static boolean getFirstRunStatus(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(context.getString(R.string.pref_key_first_run), true);
    }*/

    /*public static void setFirstRunStatus(Context context, boolean firstRunStatus) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_key_first_run), firstRunStatus);
        editor.apply();
    }*/
}