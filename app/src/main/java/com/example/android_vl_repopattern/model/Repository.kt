package com.example.android_vl_repopattern.model

import androidx.lifecycle.LiveData
import com.example.android_vl_repopattern.model.dataModels.Item
import com.example.android_vl_repopattern.model.dataModels.LogTag
import com.example.android_vl_repopattern.model.local.ItemDatabase
import com.example.android_vl_repopattern.model.remote.Api

class Repository(
    private val api: Api,
    private val database: ItemDatabase
) {
    private val _items = database.itemDAO.getAllItems()
    val items: LiveData<List<Item>>
        get() = _items

    suspend fun getItems() {
        executeDataOperation {
            val items = fetchItemListFromAPI()
            insertItemListToDatabase(items)
        }
    }

    suspend fun updateItem(item: Item) {
        executeDataOperation { database.itemDAO.updateItem(item) }
    }
    
    suspend fun insertItemToDatabase(item: Item) {
        executeDataOperation { database.itemDAO.insertItem(item) }
    }

    private suspend fun insertItemListToDatabase(items: List<Item>) {
        executeDataOperation { database.itemDAO.insertItemList(items) }
    }

    suspend fun deleteAllItems() {
        executeDataOperation { database.itemDAO.deleteAllItems() }
    }

    suspend fun deleteById(id: Long) {
        executeDataOperation { database.itemDAO.deleteById(id) }
    }

    suspend fun deleteByItem(item: Item) {
        executeDataOperation { database.itemDAO.delete(item) }
    }

    private suspend fun fetchItemListFromAPI(): List<Item> {
        return api.service.getItemList().items
    }

    private suspend fun executeDataOperation(operation: suspend () -> Unit) {
        try {
            operation()
        } catch (e: Exception) {
            e.localizedMessage?.let { LogTag.log(LogTag.REPOSITORY, it) }
        }
    }
}