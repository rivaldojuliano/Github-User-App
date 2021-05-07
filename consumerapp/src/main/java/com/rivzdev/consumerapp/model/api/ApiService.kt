package com.rivzdev.consumerapp.model.api

import com.rivzdev.githubuserapp.model.api.ApiEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private const val BASE_URL = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiInstance: ApiEndpoint = retrofit.create(ApiEndpoint::class.java)
}