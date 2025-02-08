package com.darius.wallet.network

import com.darius.wallet.network.data.Recipes
import retrofit2.http.GET

interface ApiService {

    @GET("recipes")
    suspend fun getRecipes(): Recipes
}