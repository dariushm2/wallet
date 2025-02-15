package com.darius.wallet.network.repositories

import com.darius.wallet.data.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepo {
    suspend fun getRecipes(): Flow<List<Recipe>>
}
