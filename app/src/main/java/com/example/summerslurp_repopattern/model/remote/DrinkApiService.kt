package com.example.summerslurp_repopattern.model.remote

import com.example.summerslurp_repopattern.model.datamodels.DrinkList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/"

private val logger = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val client = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface DrinkApiService {

    @GET("1/filter.php?a=Non_Alcoholic")
    suspend fun getDrinkList(): DrinkList
}

object DrinkApi {
    val retrofitService: DrinkApiService by lazy { retrofit.create(DrinkApiService::class.java) }
}