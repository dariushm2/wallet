package com.darius.wallet.ui.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darius.wallet.data.Recipe
import com.darius.wallet.network.repositories.RecipesRepo
import com.darius.wallet.ui.data.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipesRepo: RecipesRepo,
) : ViewModel() {

    private val _recipes = MutableStateFlow<UiState>(UiState.Loading)
    val recipes: StateFlow<UiState> = _recipes

    private val customExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            _recipes.emit(UiState.Error(throwable.message))
        }
        throwable.printStackTrace()
    }

    fun fetchRecipes() {
        viewModelScope.launch(customExceptionHandler) {
            recipesRepo.getRecipes().collect {
                _recipes.emit(
                    UiState.Success(
                        it.map { recipe ->
                            recipe.toUiModel()
                        }
                    )
                )
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