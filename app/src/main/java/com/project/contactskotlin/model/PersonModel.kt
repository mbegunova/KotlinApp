package com.project.contactskotlin.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "persons_table") class PersonModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var lastname: String = "",
    var middlename: String = "",
    var phone: String = "",
    var shortPhone: String = "",
    var status: String = "",
    var address: String = ""
): Parcelable