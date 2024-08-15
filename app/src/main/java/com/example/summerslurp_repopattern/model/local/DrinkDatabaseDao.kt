package com.example.summerslurp_repopattern.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.summerslurp_repopattern.model.datamodels.Drink

@Dao
interface DrinkDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(drinks: List<Drink>)

    @Query("SELECT * FROM drink_table")
    fun getAll() : LiveData<List<Drink>>

    @Query("DELETE from drink_table")
    suspend fun deleteAll()

    @Query("SELECT * from drink_table where id=:id")
    suspend fun getDrinkById(id: Long): Drink
}

