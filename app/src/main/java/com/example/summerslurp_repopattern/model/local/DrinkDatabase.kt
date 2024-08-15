package com.example.summerslurp_repopattern.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.summerslurp_repopattern.model.datamodels.Drink

@Database(entities = [Drink::class], version = 1)
abstract class DrinkDatabase : RoomDatabase() {

    abstract val drinkDatabaseDAO: DrinkDatabaseDAO

    companion object {
        private lateinit var INSTANCE: DrinkDatabase

        fun getDatabase(context: Context): DrinkDatabase {
            synchronized(DrinkDatabase::class.java) {
                if (!this::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DrinkDatabase::class.java,
                        "drink_db"
                    ).build()
                }
                return INSTANCE
            }
        }
    }
}
