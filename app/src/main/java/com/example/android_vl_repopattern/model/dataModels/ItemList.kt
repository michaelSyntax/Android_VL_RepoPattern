package com.example.android_vl_repopattern.model.dataModels

import com.squareup.moshi.Json

data class ItemList(
    @Json(name = "drinks")
    val items: List<Item>
)
