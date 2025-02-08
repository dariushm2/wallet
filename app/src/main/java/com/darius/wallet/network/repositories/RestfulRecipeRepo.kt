package com.darius.wallet.network.repositories

import androidx.compose.ui.util.fastJoinToString
import com.darius.wallet.data.Recipe
import com.darius.wallet.network.ApiService
import com.darius.wallet.network.data.Recipe as NetworkRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RestfulRecipeRepo @Inject constructor(
    private val apiService: ApiService,
) : RecipesRepo {

    override suspend fun getRecipes(): Flow<List<Recipe>> =
        flowOf(apiService.getRecipes().recipes.map { it.toDomainModel() })

    private fun NetworkRecipe.toDomainModel() =
        Recipe(
            id = id,
            name = name,
            ingredients = ingredients.fastJoinToString(),
            instructions = instructions.fastJoinToString(),
        )
}