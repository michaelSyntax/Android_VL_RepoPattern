package com.example.android_vl_repopattern.model.dataModels

import android.util.Log

enum class LogTag {
    VIEW_MODEL,
    REPOSITORY;

    companion object {
        fun log(tag: LogTag, message: String) {
            Log.e(tag.name, message)
        }
    }
}