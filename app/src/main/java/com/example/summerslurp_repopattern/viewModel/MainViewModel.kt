package com.example.summerslurp_repopattern.viewModel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.summerslurp_repopattern.model.AppRepository
import com.example.summerslurp_repopattern.model.local.DrinkDatabase
import com.example.summerslurp_repopattern.model.remote.DrinkApi
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    // TODO: init DrinkDatabase
    // TODO: init AppRepository
    // TODO: add public val drinkList: liveData

    private val _loading = MutableLiveData(View.GONE)
    val loading: LiveData<Int>
        get() = _loading

    // TODO: Add suspend fun getDrinks

    // TODO: Add suspend fun deleteAll
}