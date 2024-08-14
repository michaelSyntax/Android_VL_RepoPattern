package com.example.summerslurp_repopattern.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.summerslurp_repopattern.model.AppRepository
import com.example.summerslurp_repopattern.model.local.DrinkDatabase
import com.example.summerslurp_repopattern.model.remote.DrinkApi
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = AppRepository(DrinkApi, DrinkDatabase.getDatabase(application))

    val drinkList = repository.drinkList

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun loadData() {
        _loading.value = true
        viewModelScope.launch {
            try {
                repository.getDrinks()
                _loading.value = false
            } catch (e: Exception) {
                Log.e("ViewModel", "Es ist ein Fehler aufgetreten: $e")
                _loading.value = false
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            try {
                repository.deleteAll()
            } catch (e: Exception) {
                Log.e("ViewModel.deleteAll()", "Es ist ein Fehler aufgetreten: $e")
            }
        }
    }
}