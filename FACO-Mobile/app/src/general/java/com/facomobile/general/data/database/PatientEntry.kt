package com.facomobile.general.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class PatientEntry(@PrimaryKey val uid: String = "", var firstName: String,
                        var lastName: String?, var otherName: String?, var email: String?,
                        var phoneNumber: String?)