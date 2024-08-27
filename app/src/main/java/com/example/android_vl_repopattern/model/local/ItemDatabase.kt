package com.example.android_vl_repopattern.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android_vl_repopattern.model.dataModels.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase: RoomDatabase() {
    abstract val itemDAO: ItemDAO

    companion object {
        private lateinit var instance: ItemDatabase

        fun getDatabase(context: Context) : ItemDatabase {
            synchronized(this) {
                if(!this::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java,
                        "item_database"
                    ).build()
                }
            }
            return instance
        }
    }
}