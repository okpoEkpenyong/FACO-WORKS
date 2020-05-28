package com.stargradegroup.vtuservices.cta.utilities

import android.app.Activity
import android.app.AlertDialog


object NotificationUtils {
    fun showAlert(activity: Activity, message: String) {
        val alert = AlertDialog.Builder(activity.applicationContext)
                .setMessage(message)
                .setPositiveButton("Ok", null)
                .create()
        alert.show()
    }
}
