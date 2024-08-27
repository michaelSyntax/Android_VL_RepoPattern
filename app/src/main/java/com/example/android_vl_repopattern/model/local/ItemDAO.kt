package com.example.android_vl_repopattern.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android_vl_repopattern.model.dataModels.Item

@Dao
interface ItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemList(items: List<Item>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Query("SELECT * FROM item_table")
    fun getAllItems() : LiveData<List<Item>>

    @Query("SELECT * FROM item_table where id = :id")
    fun getItemById(id: Long): LiveData<Item>

    @Update
    suspend fun updateItem(item: Item)

    @Query("DELETE FROM item_table")
    suspend fun deleteAllItems()

    @Query("DELETE FROM item_table WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Delete
    suspend fun delete(item: Item)
}