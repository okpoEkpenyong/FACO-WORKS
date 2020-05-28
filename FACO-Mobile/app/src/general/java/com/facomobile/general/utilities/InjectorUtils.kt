package com.stargradegroup.vtuservices.cta.utilities

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


import android.content.Context
import com.facomobile.AppExecutors
import com.facomobile.general.data.AppRepository
import com.facomobile.general.data.database.AppDatabase
import com.facomobile.general.data.network.FirebaseNetworkOperations

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
object InjectorUtils {

    fun provideRepository(context: Context): AppRepository {
        val database: AppDatabase = AppDatabase.getInstance(context.applicationContext)
        val firebaseNetworkOperations: FirebaseNetworkOperations =
                FirebaseNetworkOperations.getInstance()
        val executors: AppExecutors = provideExecutors()
        return AppRepository.getInstance(database.patientDao(), firebaseNetworkOperations, executors)
    }

    fun provideExecutors(): AppExecutors {
        return AppExecutors.instance
    }

    /*fun provideMainActivityViewModelFactory(context: Context): MainActivityViewModelFactory {
        val repository = provideRepository(context)
        return MainActivityViewModelFactory(repository)
    }

    fun provideChangeFragmentViewModelFactory(context: Context): ChangeFragmentViewModelFactory {
        val repository = provideRepository(context)
        return ChangeFragmentViewModelFactory(repository)
    }

    fun provideProfileFragmentViewModelFactory(context: Context): ProfileFragmentViewModelFactory {
        val repository = provideRepository(context)
        return ProfileFragmentViewModelFactory(repository)
    }*/

}
