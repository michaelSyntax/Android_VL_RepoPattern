package com.example.android_vl_repopattern.model.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @Json(name = "strDrink")
    var title: String,

    @Json(name = "strDrinkThumb")
    val image: String
)
