package com.darius.wallet.ui.recipes

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.darius.wallet.data.Recipe
import com.darius.wallet.network.cache.RecipeDao
import com.darius.wallet.network.repositories.RecipesRepo
import com.darius.wallet.ui.CoroutineScopeViewModel
import com.darius.wallet.ui.data.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipesRepo: RecipesRepo,
    private val recipeDao: RecipeDao,
    dispatcher: CoroutineDispatcher
) : CoroutineScopeViewModel(dispatcher) {

    private val _recipes = MutableStateFlow<UiState>(UiState.Loading)
    val recipes: StateFlow<UiState> = _recipes

    private val customExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("items", "error")
        viewModelScope.launch {
            _recipes.emit(UiState.Error(throwable.message))
        }
        throwable.printStackTrace()
    }

    init {
        launch {
            _recipes.emit(UiState.Success(recipeDao.getRecipes().map { it.toUiModel() }))
        }
        fetchRecipes()
    }

    private fun fetchRecipes() {
        launch(customExceptionHandler) {
            recipesRepo.getRecipes().collect {
                it.forEach { recipe ->
                    recipeDao.insert(recipe)
                }
            }
        }
    }

    fun retry() {
        viewModelScope.launch {
            _recipes.emit(UiState.Loading)
        }
        fetchRecipes()
    }

    private fun Recipe.toUiModel() =
        UiRecipe(
            id = id,
            name = name,
            instructions = instructions,
        )
}