package com.example.android_vl_repopattern.viewModel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android_vl_repopattern.model.Repository
import com.example.android_vl_repopattern.model.dataModels.Item
import com.example.android_vl_repopattern.model.dataModels.LogTag
import com.example.android_vl_repopattern.model.local.ItemDatabase
import com.example.android_vl_repopattern.model.remote.Api
import kotlinx.coroutines.launch

class ItemViewModel(application: Application): AndroidViewModel(application) {
    private val database = ItemDatabase.getDatabase(application)
    private val repository = Repository(Api, database)
    val items = repository.items

    private var _selectedItem = MutableLiveData<Item>()
    val selectedItem: LiveData<Item>
        get() = _selectedItem

    private val _loading = MutableLiveData(View.GONE)
    val loading: LiveData<Int>
        get() = _loading


    fun loadData() {
        executeWithLoggingAndLoading({ repository.getItems() }, true)
    }

    fun selectItemById(id: Long) {
        _selectedItem.postValue(database.itemDAO.getItemById(id).value)
    }

    fun addNewItem(item: Item) {
        executeWithLoggingAndLoading({
            repository.insertItemToDatabase(item)
        })
    }

    fun updateItem(name: String) {
        _selectedItem.value?.title = name
        executeWithLoggingAndLoading({
            _selectedItem.value?.let { repository.updateItem(it) }
        })
    }

    fun deleteAllItems() {
        executeWithLoggingAndLoading({ repository.deleteAllItems() })
    }

    fun deleteItemById() {
        executeWithLoggingAndLoading({
            _selectedItem.value?.let { repository.deleteById(it.id) }
        })
    }

    fun deleteItem() {
        executeWithLoggingAndLoading({
            selectedItem.value?.let { repository.deleteByItem(it) }
        })
    }

    private fun setLoadingState(isLoading: Boolean) {
        _loading.value = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun executeWithLoggingAndLoading(
        action: suspend () -> Unit,
        manageLoadingState: Boolean = false
    ) {
        viewModelScope.launch {
            if (manageLoadingState) setLoadingState(true)
            try {
                action()
            } catch (e: Exception) {
                e.localizedMessage?.let { LogTag.log(LogTag.VIEW_MODEL, it) }
            } finally {
                if (manageLoadingState) setLoadingState(false)
            }
        }
    }
}