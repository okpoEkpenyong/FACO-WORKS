package com.facomobile

import android.app.Application
import androidx.work.Configuration


class CustomApplication : Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.INFO)
                .build()
    }
}