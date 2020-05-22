package com.facomobile

import android.app.Application


class CustomApplication : Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration {
        return androidx.work.Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.INFO)
                .build()
    }
}