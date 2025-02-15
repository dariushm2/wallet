package com.darius.wallet.network.repositories

import androidx.compose.ui.util.fastJoinToString
import com.darius.wallet.data.Recipe
import com.darius.wallet.network.ApiService
import com.darius.wallet.network.cache.RecipeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.darius.wallet.network.data.Recipe as NetworkRecipe

class RestfulRecipeRepo @Inject constructor(
    private val apiService: ApiService,
    private val recipeDao: RecipeDao,
) : RecipesRepo {

    override suspend fun getRecipes(): Flow<List<Recipe>> =
        recipeDao.getRecipes().also {
            getNetwork()
        }

    private suspend fun getNetwork() =
        apiService.getRecipes().recipes.forEach {
            recipeDao.insert(it.toDomainModel())
        }

    private fun NetworkRecipe.toDomainModel() =
        Recipe(
            id = id,
            name = name,
            ingredients = ingredients.fastJoinToString(),
            instructions = instructions.fastJoinToString(),
        )
}
