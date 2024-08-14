package com.example.summerslurp_repopattern.model

import com.example.summerslurp_repopattern.model.local.DrinkDatabase
import com.example.summerslurp_repopattern.model.remote.DrinkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(
    private val api: DrinkApi,
    private val database: DrinkDatabase
) {

    // TODO: Create public val drinkList: LiveData from dao

    // TODO: Add suspend fun getDrinks

    // TODO: Add suspend fun deleteAll
}