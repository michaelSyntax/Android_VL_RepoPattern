package com.example.summerslurp_repopattern.model.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "drink_table")
data class Drink(
    @PrimaryKey
    @Json(name = "idDrink")
    val id: Long,

    @Json(name = "strDrink")
    val name: String,

    @Json(name = "strDrinkThumb")
    val picture: String
)
