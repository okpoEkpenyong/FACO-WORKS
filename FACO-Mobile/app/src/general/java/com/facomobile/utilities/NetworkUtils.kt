package com.facomobile.utilities

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtils {

    private val LOG_TAG = NetworkUtils::class.java.simpleName

    /**
     * Checks if the device is currently capable of connecting to and accessing the internet.
     *
     * @param context the context of the application
     * @return the connection status of the device
     */
    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
