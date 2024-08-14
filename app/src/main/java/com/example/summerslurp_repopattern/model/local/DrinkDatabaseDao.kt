package com.example.summerslurp_repopattern.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.summerslurp_repopattern.model.datamodels.Drink

@Dao
interface DrinkDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(drinks: List<Drink>)

    @Query("SELECT * FROM drink")
    fun getAll() : LiveData<List<Drink>>

    @Query("DELETE from Drink")
    suspend fun deleteAll()
}

