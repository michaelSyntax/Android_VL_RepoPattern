package com.example.summerslurp_repopattern.viewModel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.summerslurp_repopattern.model.AppRepository
import com.example.summerslurp_repopattern.model.datamodels.Drink
import com.example.summerslurp_repopattern.model.local.DrinkDatabase
import com.example.summerslurp_repopattern.model.remote.DrinkApi
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val database = DrinkDatabase.getDatabase(application)
    private val repository = AppRepository(DrinkApi, database)
    val listOfDrinks = repository.listOfDrinks

    private val _selectedDrink = MutableLiveData<Drink>()
    val selectedDrink: LiveData<Drink>
        get() = _selectedDrink

    private val _loading = MutableLiveData(View.GONE)
    val loading: LiveData<Int>
        get() = _loading

    fun loadData() {
        _loading.value = View.VISIBLE

        viewModelScope.launch {
            try {
                repository.getDrinks()
                _loading.value = View.GONE
            } catch (e: Exception) {
                Log.e("ViewModel", "Es ist ein Fehler aufgetreten: $e")
                _loading.value = View.GONE
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            try {
                repository.deleteAll()
            } catch (e: Exception) {
                Log.e("ViewModel", "Es ist ein Fehler aufgetreten: $e")
            }
        }
    }

    fun setDrinkById(id: Long) {
        viewModelScope.launch {
            try {
                _selectedDrink.value = repository.getDrinkById(id)
            } catch (e: Exception) {
                Log.e("ViewModel", "Es ist ein Fehler aufgetreten: $e")
            }
        }
    }
}