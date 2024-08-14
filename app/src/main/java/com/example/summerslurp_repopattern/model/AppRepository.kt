package com.example.summerslurp_repopattern.model

import com.example.summerslurp_repopattern.model.local.DrinkDatabase
import com.example.summerslurp_repopattern.model.remote.DrinkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(
    private val api: DrinkApi,
    private val database: DrinkDatabase
) {
    val drinkList = database.drinkDatabaseDAO.getAll()

    suspend fun getDrinks() {
        withContext(Dispatchers.IO) {

            // Holt die Daten von der API
            val newDrinkList = api.retrofitService.getDrinkList().drinks

            // Fügt die Daten in die Datenbank ein
            database.drinkDatabaseDAO.insertAll(newDrinkList)
        }
    }
}