package com.example.summerslurp_repopattern.model

import androidx.lifecycle.LiveData
import com.example.summerslurp_repopattern.model.datamodels.Drink
import com.example.summerslurp_repopattern.model.local.DrinkDatabase
import com.example.summerslurp_repopattern.model.remote.DrinkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(
    private val api: DrinkApi,
    private val database: DrinkDatabase
) {

    val listOfDrinks: LiveData<List<Drink>> = database.drinkDatabaseDAO.getAll()

    suspend fun getDrinks() {
        val listOfDrinks: List<Drink> = fetchListOfDrinksFromAPI()
        database.drinkDatabaseDAO.insertAll(listOfDrinks)
    }

    suspend fun deleteAll() {
        database.drinkDatabaseDAO.deleteAll()
    }

    private suspend fun fetchListOfDrinksFromAPI(): List<Drink> {
        return api.retrofitService.getDrinkList().drinks
    }
}